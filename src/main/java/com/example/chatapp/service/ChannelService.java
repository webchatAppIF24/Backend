package com.webchat.Seohyun.channel.service;


import com.webchat.Seohyun.channel.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public Channel createChannel(String name) {
        Channel channel = new Channel();
        channel.setName(name);
        return channelRepository.save(channel);
    }

    public Optional<Channel> getChannel(Long id) {
        return channelRepository.findById(id);
    }

    // 추가적인 채널 기능
}
