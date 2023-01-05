package com.project.api.entity;

import lombok.Getter;

import javax.persistence.*;



@Getter
@Entity
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JoinColumn(name ="post_id")
    private Long id;
    private String title;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "username")
    private User writer;

    private String password;
    private String content;

    public Post() {}

    public Post(String title, User writer, String password, String content) {

        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    public void update(String title, String content) {

        this.title = title;
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
