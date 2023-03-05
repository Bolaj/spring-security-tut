package com.portfolio.springsecurityproject.controller;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.model.UserModel;
import com.portfolio.springsecurityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel)
    {
        User user = userService.registerUser(userModel);


    }
}
