package com.example.chatapp.controller;


import com.webchat.Seohyun.channel.model.Message;
import com.webchat.Seohyun.channel.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/channel/{channelId}")
    public List<Message> getMessagesByChannel(@PathVariable Long channelId) {
        return messageService.getMessagesByChannelId(channelId);
    }
}
