package com.example.chatapp.domain.channel;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private com.example.chatapp.domain.channel.UserProfile author;   // 작성자 프로필
    private LocalDateTime createdAt; // 작성 날짜 및 시간
    private String content; // 메시지 내용

    // 날짜 표시 여부 (날짜 표시를 한 번만 위에 표시하기 위해)
    private boolean showDate;

    // Getter and Setter
}
