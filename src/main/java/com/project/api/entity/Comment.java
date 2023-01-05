package com.project.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "Post_id")
    private Long postId;

    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public Comment(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    private void update(String content) {
        this.content = content;
    }
}
