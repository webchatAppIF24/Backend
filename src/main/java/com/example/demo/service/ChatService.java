package com.example.demo.service;

import com.example.demo.model.Message;
import java.util.List;
import com.example.demo.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService
{
    private final ChatRepository chatRepository;

    public ChatService(final ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Message> getChatHistory(final String userId, final String friendId) {
        return this.chatRepository.findBySenderAndReceiverOrReceiverAndSender(userId, friendId, friendId, userId);
    }
}
