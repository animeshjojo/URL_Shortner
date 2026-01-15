package com.example.url_shortner.Repository;

import com.example.url_shortner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositry extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);
}
