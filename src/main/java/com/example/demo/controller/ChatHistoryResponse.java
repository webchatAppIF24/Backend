package com.example.demo.controller;

import com.example.demo.model.Message;
import java.util.List;

public class ChatHistoryResponse
{
    private List<Message> messages;

    public ChatHistoryResponse(final List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }
}
