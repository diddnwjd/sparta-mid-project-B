package com.project.api.controller;

import com.project.api.dto.CommentResponse;
import com.project.api.dto.CreateCommnetRequest;
import com.project.api.jwt.JwtUtil;
import com.project.api.repository.CommentRepository;
import com.project.api.service.CommentService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    @PostMapping("/api/posts/{postId}/comment")
    public CommentResponse createComment(@PathVariable Long postId, @RequestBody CreateCommnetRequest createCommnetRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                return commentService.createComment(postId, createCommnetRequest, username);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }
}
