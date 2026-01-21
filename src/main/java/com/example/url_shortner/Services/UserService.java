package com.example.url_shortner.Services;

import com.example.url_shortner.Entity.UrlData;
import com.example.url_shortner.Entity.User;
import com.example.url_shortner.Repository.UserRepositry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepositry userRepositry;

    public UserService(UserRepositry userRepositry) {
        this.userRepositry = userRepositry;
    }

    public List<User> getAllUsers() {
        return userRepositry.findAll();
    }

    public List<UrlData> getUrlDataByUser(String username) {
        return userRepositry.findUserByUserName(username).getUrls();
    }
}
