package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.Message;
import java.util.List;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.service.ChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/private-chat" })
public class ChatController
{
    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping({ "/history" })
    public ChatHistoryResponse getChatHistory(@RequestBody final ChatHistoryRequest request, @RequestHeader("Authorization") final String token) {
        final List<Message> messages = this.chatService.getChatHistory(request.getUserId(), request.getFriendId());
        return new ChatHistoryResponse(messages);
    }
}
