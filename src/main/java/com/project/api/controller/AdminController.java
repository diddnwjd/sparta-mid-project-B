package com.project.api.controller;

import com.project.api.dto.DeletePostRequest;
import com.project.api.dto.PostResponse;
import com.project.api.dto.UpdatePostRequest;
import com.project.api.entity.User;
import com.project.api.entity.UserRoleEnum;
import com.project.api.jwt.JwtUtil;
import com.project.api.repository.UserRepository;
import com.project.api.service.AdminService;
import com.project.api.service.PostService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    @PutMapping("/api/admin/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("없는 유저"));
                if (!user.getRole().equals(UserRoleEnum.ADMIN)) { throw new IllegalArgumentException("관리자가 아닙니다"); }
                return adminService.updatePost(postId, updatePostRequest);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }

    @DeleteMapping("/api/admin/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody DeletePostRequest deletePostRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("없는 유저"));
                if (!user.getRole().equals(UserRoleEnum.ADMIN)) { throw new IllegalArgumentException("관리자가 아닙니다"); }
                adminService.deletePost(postId, deletePostRequest);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }

    @DeleteMapping("/api/admin/{postId}/comment/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("없는 유저"));
                if (!user.getRole().equals(UserRoleEnum.ADMIN)) { throw new IllegalArgumentException("관리자가 아닙니다"); }
                adminService.deleteComment(postId, commentId);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }

}
