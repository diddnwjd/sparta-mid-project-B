package com.project.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // Getter 와 NoArgsConstructor 를 사용하면 알아서 데이터까 들어감
public class CreatePostRequest {
    private String title;
    private String writer;
    private String password;
    private String content;

    public CreatePostRequest(String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }
}
