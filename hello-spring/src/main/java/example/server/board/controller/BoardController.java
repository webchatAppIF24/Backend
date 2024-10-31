package example.board.controller;

import example.board.model.Comment;
import example.board.model.Post;
import example.board.service.CommentService;
import example.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    // 게시글 목록 조회
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 게시글 작성
    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post.getTitle(), post.getContent(), post.getAuthor());
    }

    // 특정 게시글 조회
    @GetMapping("/posts/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    // 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.createComment(postId, comment.getContent(), comment.getAuthor());
    }
}
