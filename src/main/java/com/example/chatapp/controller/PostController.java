package com.example.chatapp.controller;


import com.webchat.Seohyun.board.model.Post;
import com.webchat.Seohyun.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // 전체 게시글 목록 조회
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 게시글 작성
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post.getTitle(), post.getContent(), post.getAuthor());
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPostData) {
        Post updatedPost = postService.updatePost(id, updatedPostData);
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }
}
