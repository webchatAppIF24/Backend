package com.webchat.Seohyun.channel.service;


import com.webchat.Seohyun.channel.model.Message;
import com.webchat.Seohyun.channel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChannelId(Long channelId) {
        // 특정 채널 ID로 메시지 조회 (조건부 조회 필요 시 작성)
        return messageRepository.findAll(); // 조건 추가 필요
    }

    // 추가적인 메시지 기능
}
