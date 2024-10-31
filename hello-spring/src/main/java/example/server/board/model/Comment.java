package example.board.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;            // 댓글 작성자 (익명 처리)
    private String content;           // 댓글 내용
    private LocalDateTime createdAt;  // 작성 날짜 및 시간

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;                // 댓글이 달린 게시물

    // Getters and Setters
}
