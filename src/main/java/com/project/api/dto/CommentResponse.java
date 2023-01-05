package com.project.api.dto;

import com.project.api.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long commentId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.writer = comment.getWriter().getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
    }
}
