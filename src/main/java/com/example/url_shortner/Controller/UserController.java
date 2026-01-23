package com.example.url_shortner.Controller;

import com.example.url_shortner.Entity.UrlData;
import com.example.url_shortner.Services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UrlData>  getUrlData(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUrlDataByUser(username);
    }

    //post mapping where authenticated user will enter long url and will get short url and in db the list of that user will be updated

}
