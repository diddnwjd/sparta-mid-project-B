package com.project.api.controller;

import com.project.api.dto.CreatePostRequest;
import com.project.api.dto.DeletePostRequest;
import com.project.api.dto.PostResponse;
import com.project.api.dto.UpdatePostRequest;
import com.project.api.entity.Post;
import com.project.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 전체 게시물 조회
    @GetMapping("/api/posts")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    // 게시물 생성
    @PostMapping("/api/posts")
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {   // () = createPostRequest 값을 전달 받음
        postService.createPost(createPostRequest);
    }

    // 선택 게시물 조회
    @GetMapping("/api/posts/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    // 선택 게시물 수정
    @PutMapping("/api/posts/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        postService.updatePost(postId, updatePostRequest);
    }

    @DeleteMapping("/api/posts/{postId}")
    public void deletePost(@PathVariable Long postId, @RequestBody DeletePostRequest deletePostRequest) {
        postService.deletePost(postId, deletePostRequest);
    }

}
