package com.portfolio.springsecurityproject.controller;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.event.RegistrationCompleteEvent;
import com.portfolio.springsecurityproject.model.UserModel;
import com.portfolio.springsecurityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Welcome to Api";
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request)
    {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success";

    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
