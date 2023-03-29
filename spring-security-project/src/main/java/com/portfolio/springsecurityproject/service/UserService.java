package com.portfolio.springsecurityproject.service;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.entity.VerificationToken;
import com.portfolio.springsecurityproject.model.UserModel;

public interface UserService{
    User registerUser(UserModel userModel);

    void saveVerficationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
