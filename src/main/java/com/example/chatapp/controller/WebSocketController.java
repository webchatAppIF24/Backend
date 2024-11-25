package com.example.chatapp.controller;

import com.example.chatapp.domain.board.Post;
import com.example.chatapp.service.PostService;
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
        // Post 객체에서 각 필드를 추출해 createPost 메서드에 전달
        return postService.createPost(post.getTitle(), post.getContent(), post.getAuthor());
    }
}
