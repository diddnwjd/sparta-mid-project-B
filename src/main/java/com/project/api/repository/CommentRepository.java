package com.project.api.repository;

import com.project.api.entity.Comment;
import com.project.api.entity.Post;
import com.project.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByCreateAt();
    Optional<Comment> findByUsername(String username);
}
