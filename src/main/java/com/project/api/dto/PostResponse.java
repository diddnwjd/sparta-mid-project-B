package com.project.api.dto;

import com.project.api.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private final String title;
    private final String writer;
    private final String content;
    private final LocalDateTime createAt;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.createAt = post.getCreateAt();
    }
}

