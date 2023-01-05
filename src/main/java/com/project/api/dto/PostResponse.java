package com.project.api.dto;

import com.project.api.entity.Comment;
import com.project.api.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponse {
    private final String title;
    private final String writer;
    private final String content;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter().getUsername();
        this.content = post.getContent();
    }
}

