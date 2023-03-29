package com.portfolio.springsecurityproject.controller;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.entity.VerificationToken;
import com.portfolio.springsecurityproject.event.RegistrationCompleteEvent;
import com.portfolio.springsecurityproject.model.UserModel;
import com.portfolio.springsecurityproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@Slf4j
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
    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request)
    {
        VerificationToken verificationToken
                = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent.";


    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token="
                + verificationToken.getToken();

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }

    @PostMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token)
    {

        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified";
        }
        return "Bad User";
    }
    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
