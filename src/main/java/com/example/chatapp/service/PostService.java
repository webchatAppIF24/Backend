package com.example.chatapp.service;

import com.example.chatapp.domain.board.Post;
import com.example.chatapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시글 전체 목록 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 새로운 게시글 생성
    public Post createPost(String title, String content, String author) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author != null ? author : "익명");
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    // ID로 게시글 조회
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    // 게시글 수정
    public Post updatePost(Long id, Post updatedPostData) {
        Optional<Post> existingPostOpt = postRepository.findById(id);

        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setTitle(updatedPostData.getTitle());
            existingPost.setContent(updatedPostData.getContent());
            existingPost.setAuthor(updatedPostData.getAuthor());
            return postRepository.save(existingPost);
        }
        return null;
    }
}
