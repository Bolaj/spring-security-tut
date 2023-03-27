package com.portfolio.springsecurityproject.listener;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.event.RegistrationCompleteEvent;
import com.portfolio.springsecurityproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j

public class RegCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        //verifcation token for the user
        User  user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerficationTokenForUser(token, user);
        //send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token="
                + token;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }
}
