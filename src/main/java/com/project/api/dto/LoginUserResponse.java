package com.project.api.dto;

import lombok.Getter;

@Getter
public class LoginUserResponse {
    private final Long statusCode;
    private final String statusMessage;

    public LoginUserResponse(Long statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
