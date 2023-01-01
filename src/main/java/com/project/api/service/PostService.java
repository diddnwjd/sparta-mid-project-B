package com.project.api.service;

import com.project.api.dto.CreatePostRequest;
import com.project.api.dto.DeletePostRequest;
import com.project.api.dto.PostResponse;
import com.project.api.dto.UpdatePostRequest;
import com.project.api.entity.Post;
import com.project.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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
    public void createPost(CreatePostRequest createPostRequest) {
        Post post = new Post(createPostRequest.getTitle(), createPostRequest.getWriter(), createPostRequest.getPassword(), createPostRequest.getContent());
        postRepository.save(post);
    }

    @Transactional
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        return new PostResponse(post);
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest updatePostRequest) {
        Post postSaved = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        if (postSaved.isValidPassword(updatePostRequest.getPassword())) {
            postSaved.update(updatePostRequest.getTitle(), updatePostRequest.getWriter(), updatePostRequest.getContent());
            postRepository.save(postSaved); // update 로 변경된 나머지를 다시 DB에 저장
        } else {
            throw new IllegalArgumentException("패스워트가 다릅니다");
        }
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequest deletePostRequest) {
        Post postDelete = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("id 없음"));  // Controller 에서 받은 id 값 번째 게시글을 postSaved 선언(?)
        if (postDelete.isValidPassword(deletePostRequest.getPassword())) {
            postRepository.delete(postDelete);
            System.out.println("success");
        } else {
            throw new IllegalArgumentException("패스워드가 다릅니다");
        }
    }
}
