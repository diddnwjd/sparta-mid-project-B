package com.project.api.service;

import com.project.api.dto.CreateUserRequest;
import com.project.api.dto.CreateUserResponse;
import com.project.api.entity.User;
import com.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이름입니다.");
        }
        User user = new User(createUserRequest.getUsername(), createUserRequest.getPassword());
        userRepository.save(user);
    }
}