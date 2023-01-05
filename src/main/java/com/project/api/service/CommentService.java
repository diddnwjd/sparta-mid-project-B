package com.project.api.service;

import com.project.api.dto.CommentResponse;
import com.project.api.dto.CreateCommnetRequest;

import com.project.api.entity.Comment;
import com.project.api.entity.Post;
import com.project.api.entity.User;
import com.project.api.repository.CommentRepository;
import com.project.api.repository.PostRepository;
import com.project.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponse createComment(Long postId, CreateCommnetRequest createCommnetRequest, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Comment comment = new Comment(post, user, createCommnetRequest.getContent());
        commentRepository.save(comment);
        return new CommentResponse(comment);

    }
}
