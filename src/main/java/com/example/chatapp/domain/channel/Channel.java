package com.example.chatapp.domain.channel;

import jakarta.persistence.*;
import java.util.List;

@Entity // JPA 엔티티로 등록
@Table(name = "channels") // 데이터베이스 테이블 이름
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략
    private Long id;

    @Column(nullable = false, unique = true) // 이름 필드 제약 조건
    private String name;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages; // 채널의 메시지 목록

    // 기본 생성자
    public Channel() {}

    // 모든 필드의 생성자
    public Channel(String name) {
        this.name = name;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
