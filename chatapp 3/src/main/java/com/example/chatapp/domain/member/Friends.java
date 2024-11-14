package com.example.chatapp.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendsId;

    @Column(length = 20, nullable = false)
    private Long MemberId;

    @Column(nullable = false)
    private String createdAt;

    @OneToMany(mappedBy = "friends")
    private Set<MemberHasFriends> MemberHasFriends = new HashSet<>();

}