package com.project.api.controller;

import com.project.api.dto.*;
import com.project.api.entity.Post;
import com.project.api.jwt.JwtUtil;
import com.project.api.service.PostService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtUtil jwtUtil;

    // 전체 게시물 조회
    @GetMapping("/api/posts")
    public List<PostCommentResponse> getPosts() {
        return postService.getPosts();
    }

    // 게시물 생성
    @PostMapping("/api/posts")
    public PostResponse createPost(@RequestBody CreatePostRequest createPostRequest, HttpServletRequest request) {   // () = createPostRequest 값을 전달 받음
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String requestingUser = claims.getSubject();
                return postService.createPost(createPostRequxxxxest, requestingUser);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }

    // 선택 게시물 조회
    @GetMapping("/api/posts/{postId}")
    public List<PostCommentResponse> getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    // 선택 게시물 수정
    @PutMapping("/api/posts/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                return postService.updatePost(postId, updatePostRequest, username);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }

    @DeleteMapping("/api/posts/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody DeletePostRequest deletePostRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
                postService.deletePost(postId, deletePostRequest, username);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됌"); }
    }
}
