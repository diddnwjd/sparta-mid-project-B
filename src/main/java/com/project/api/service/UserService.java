package com.project.api.service;

import com.project.api.dto.CreateUserRequest;
import com.project.api.entity.User;
import com.project.api.entity.UserRoleEnum;
import com.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이름입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (createUserRequest.isAdmin()) {
            if (!createUserRequest.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(createUserRequest.getUsername(), createUserRequest.getPassword(), createUserRequest.getRole());
        userRepository.save(user);
    }
}