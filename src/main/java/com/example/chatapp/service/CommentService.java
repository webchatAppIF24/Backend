package com.example.chatapp.service;

import com.example.chatapp.domain.board.Comment;
import com.example.chatapp.domain.board.Post;
import com.example.chatapp.repository.CommentRepository;
import com.example.chatapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // 특정 게시물의 모든 댓글 조회
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 댓글 작성
    public Comment createComment(Long postId, String content, String author) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setAuthor(author != null ? author : "익명"); // 익명 처리
            comment.setCreatedAt(LocalDateTime.now());
            comment.setPost(postOptional.get());
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post not found");
        }
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, String content) {
        Optional<Comment> existingCommentOpt = commentRepository.findById(commentId);
        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();
            existingComment.setContent(content);
            return commentRepository.save(existingComment);
        }
        return null;
    }

    // 댓글 삭제
    public boolean deleteComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }
}
