package com.project.api.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Entity
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;

    public Post() {}

    public Post(String title, String writer, String password, String content) {

        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    public void update(String title, String writer, String content) {

        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public boolean isValidPassword(String inputPassword) {
        if (inputPassword.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }
}
