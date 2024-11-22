package com.example.chatapp.repository;


import com.example.chatapp.domain.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    // 추가적인 채널 조회 메서드 필요 시 선언
}
