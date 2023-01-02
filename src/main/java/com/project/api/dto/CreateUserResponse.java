package com.project.api.dto;

import lombok.Getter;

@Getter
public class CreateUserResponse {
    private final Long statusCode;
    private final String statusMessage;
    public CreateUserResponse(Long statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}