package com.project.api.controller;

import com.project.api.dto.CreateUserRequest;
import com.project.api.dto.CreateUserResponse;
import com.project.api.dto.LoginUserRequest;
import com.project.api.dto.LoginUserResponse;
import com.project.api.jwt.JwtUtil;
import com.project.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/signup")
    public CreateUserResponse createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
        return new CreateUserResponse(200L, "회원가입 완료");
    }

    @PostMapping("/api/login")
    public LoginUserResponse loginUser(@RequestBody LoginUserRequest loginUserRequest, HttpServletResponse response) {
        String generatedToken = userService.loginUser(loginUserRequest);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return new LoginUserResponse(200L, "로그인 완료");
    }
}