package com.project.api.dto;

import lombok.Getter;

@Getter
public class LoginUserRequest {
    private String username;
    private String password;
}