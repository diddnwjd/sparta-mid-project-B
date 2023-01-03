package com.project.api.service;

import com.project.api.dto.CreateUserRequest;
import com.project.api.dto.LoginUserRequest;
import com.project.api.entity.User;
import com.project.api.entity.UserRoleEnum;
import com.project.api.jwt.JwtUtil;
import com.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
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

    @Transactional(readOnly = true)
    public String loginUser(LoginUserRequest loginUserRequest) {  // void -> JwtInfo
        String username = loginUserRequest.getUsername();
        String password = loginUserRequest.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String generatedToken = jwtUtil.createToken(user.getUsername(), user.getRole());   // 로그인 관련된 비즈니스 처리는 여기서
        return generatedToken;
    }
}