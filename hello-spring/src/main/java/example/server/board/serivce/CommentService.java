package example.board.service;

import example.board.model.Comment;
import example.board.model.Post;
import example.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

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
}
