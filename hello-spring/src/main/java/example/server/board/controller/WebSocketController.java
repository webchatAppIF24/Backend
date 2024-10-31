package example.server.board.controller;

import example.server.board.model.Post;
import example.board.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private PostService postService;

    @MessageMapping("/post")
    @SendTo("/topic/posts")
    public Post broadcastPost(Post post) {
        return postService.createPost(post);
    }
}
