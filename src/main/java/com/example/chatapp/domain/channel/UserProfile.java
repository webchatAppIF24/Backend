package com.example.chatapp.domain.channel;

import jakarta.persistence.*;
import java.util.List;

@Entity // JPA 엔티티로 등록
@Table(name = "user_profiles") // 데이터베이스 테이블 이름 매핑
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long id;

    @Column(nullable = false, unique = true) // 닉네임은 고유값
    private String nickname;

    @Column(nullable = true) // 프로필 이미지 URL은 필수가 아님
    private String profileImageUrl;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true) // Message와 양방향 관계
    private List<Message> messages;

    // 기본 생성자
    public UserProfile() {}

    // 생성자
    public UserProfile(String nickname, String profileImageUrl) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
