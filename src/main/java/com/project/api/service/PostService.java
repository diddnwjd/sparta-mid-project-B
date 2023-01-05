package com.project.api.service;

import com.project.api.dto.CreatePostRequest;
import com.project.api.dto.DeletePostRequest;
import com.project.api.dto.PostResponse;
import com.project.api.dto.UpdatePostRequest;
import com.project.api.entity.Post;
import com.project.api.entity.User;
import com.project.api.jwt.JwtUtil;
import com.project.api.repository.PostRepository;
import com.project.api.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreateAt();
        List<PostResponse> postResponsesList = new ArrayList<>();
        for (Post post : posts) {
            postResponsesList.add(new PostResponse(post));
        }
        return postResponsesList;
    }

    @Transactional
    public PostResponse createPost(CreatePostRequest createPostRequest, String requestingUser) {
        User user = userRepository.findByUsername(requestingUser).orElseThrow(() -> new IllegalArgumentException("동일한 유저가 아님"));
        Post post = new Post(createPostRequest.getTitle(), user, createPostRequest.getPassword(), createPostRequest.getContent());
        postRepository.save(post);
        return new PostResponse(post);
    }


    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        return new PostResponse(post);
    }

    @Transactional
    public PostResponse updatePost(Long postId, UpdatePostRequest updatePostRequest, String requestingUser) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));

        if (!post.getWriter().equals(requestingUser)) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다");
        }

        post.update(updatePostRequest.getTitle(), updatePostRequest.getContent());
        postRepository.save(post); // update 로 변경된 나머지를 다시 DB에 저장
        return new PostResponse(post);
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequest deletePostRequest, String username) {
        Post postDelete = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));  // Controller 에서 받은 id 값 번째 게시글을 postSaved 선언(?)

        if (!postDelete.getWriter().equals(username)) {
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다");
        }

        postRepository.delete(postDelete);
        System.out.println("success");
    }
}
