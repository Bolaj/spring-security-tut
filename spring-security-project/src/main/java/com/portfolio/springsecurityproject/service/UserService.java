package com.portfolio.springsecurityproject.service;

import com.portfolio.springsecurityproject.entity.User;
import com.portfolio.springsecurityproject.model.UserModel;

public interface UserService{
    User registerUser(UserModel userModel);

}
