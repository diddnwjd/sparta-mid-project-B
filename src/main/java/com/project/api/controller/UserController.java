package com.project.api.controller;

import com.project.api.dto.CreateUserRequest;
import com.project.api.dto.CreateUserResponse;
import com.project.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}