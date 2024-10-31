package example.board.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // 게시물 번호

    private String title;         // 게시물 제목
    private String content;       // 게시물 내용
    private String author;        // 작성자 (익명 처리)
    private LocalDateTime createdAt; // 작성 날짜 및 시간

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments; // 게시물에 달린 댓글들

    // Getters and Setters
}
