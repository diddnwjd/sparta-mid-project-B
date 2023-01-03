package com.project.api.dto;

import com.project.api.entity.UserRoleEnum;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class CreateUserRequest {
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[~!@#$%^&*=,.?])[A-Za-z0-9~!@#$%^&*=,.?]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자(~!@#$%^&*=,.?)로 구성되어야 합니다.")
    private String password;
    private UserRoleEnum role;
    private final boolean admin = false;
    private final String adminToken = "";
}