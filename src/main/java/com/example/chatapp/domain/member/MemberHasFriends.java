package com.example.chatapp.domain.member;

import jakarta.persistence.*;

@Entity
public class MemberHasFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Member friend;

}