package com.example.url_shortner.Controller;

import com.example.url_shortner.Dto.UserLoginDto;
import com.example.url_shortner.Services.PublicService;
import com.example.url_shortner.Services.UserService;
import com.example.url_shortner.Utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {


    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager=authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserLoginDto userLoginDto) {
        try{
            if(userLoginDto.getPassword().isEmpty() || userLoginDto.getUserName().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or Password is empty");
            }
            userService.saveUser(userLoginDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userdto){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userdto.getUserName(), userdto.getPassword()));
            String jwt = jwtUtil.generateToken(userdto.getUserName());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
