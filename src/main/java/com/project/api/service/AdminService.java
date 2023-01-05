package com.project.api.service;

import com.project.api.dto.*;
import com.project.api.entity.Comment;
import com.project.api.entity.Post;
import com.project.api.entity.User;
import com.project.api.entity.UserRoleEnum;
import com.project.api.repository.CommentRepository;
import com.project.api.repository.PostRepository;
import com.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostResponse updatePost(Long postId, UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        post.update(updatePostRequest.getTitle(), updatePostRequest.getContent());
        postRepository.save(post);
        return new PostResponse(post);
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequest deletePostRequest) {
        Post postDelete = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        postRepository.delete(postDelete);
        System.out.println("관리자 권한으로 삭제됌");
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        commentRepository.delete(comment);
        System.out.println("관리자 권한으로 삭제됌");
    }
}
