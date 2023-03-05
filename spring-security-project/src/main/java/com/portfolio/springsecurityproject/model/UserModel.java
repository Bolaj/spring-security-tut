package com.portfolio.springsecurityproject.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
}
