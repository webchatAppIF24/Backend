package com.example.chatapp.domain.member;

import jakarta.persistence.*;

@Entity
public class MemberHasFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "friends_friends_id", nullable = false)
    private Friends friends;

}