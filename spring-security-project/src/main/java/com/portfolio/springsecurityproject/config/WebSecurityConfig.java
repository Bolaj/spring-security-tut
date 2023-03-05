package com.portfolio.springsecurityproject.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}
