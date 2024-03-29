package com.portfolio.springsecurityproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello","/register","/verifyRegistration","/resendVerificationToken").permitAll()
                .anyRequest()
                .authenticated();
        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }






}
