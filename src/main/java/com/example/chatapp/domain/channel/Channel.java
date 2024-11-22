package com.example.chatapp.domain.channel;

import java.util.List;

public class Channel {
    private Long id;
    private String name;
    private List<Message> messages; // 채널에 포함된 메시지 목록

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter
}
