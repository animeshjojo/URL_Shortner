package com.example.url_shortner.Services;

import com.example.url_shortner.Dto.UserLoginDto;
import com.example.url_shortner.Entity.UrlData;
import com.example.url_shortner.Entity.User;
import com.example.url_shortner.Repository.UserRepositry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private UserRepositry userRepositry;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepositry userRepositry) {
        this.userRepositry = userRepositry;
    }

    public List<User> getAllUsers() {
        return userRepositry.findAll();
    }

    public List<UrlData> getUrlDataByUser(String username) {
        return userRepositry.findUserByUserName(username).getUrls();
    }

    public void saveUser(UserLoginDto userLoginDto) {
        User user = new User();
        user.setUserName(userLoginDto.getUserName());
        user.setPassword(passwordEncoder.encode(userLoginDto.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userRepositry.save(user);
    }
}
