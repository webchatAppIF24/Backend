package com.example.chatapp.domain.board;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시물 번호

    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private String author; // 작성자 (익명 처리)
    private LocalDateTime createdAt; // 작성 날짜 및 시간

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<com.example.chatapp.domain.board.Comment> comments;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<com.example.chatapp.domain.board.Comment> getComments() {
        return comments;
    }

    public void setComments(List<com.example.chatapp.domain.board.Comment> comments) { this.comments = comments;}
}
