package com.example.socketkafkatester.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserRequest {
    @Email(message = "Email invalid format")
    private String email;
    @Size(min = 5, message = "Password size must be greater than 5")
    private String password;
}
