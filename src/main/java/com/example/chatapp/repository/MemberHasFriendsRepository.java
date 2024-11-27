package com.example.chatapp.repository;

import com.example.chatapp.domain.member.Member;
import com.example.chatapp.domain.member.MemberHasFriends;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberHasFriendsRepository extends JpaRepository<MemberHasFriends, Long> {

    boolean existsByMemberAndFriend(Member currentUser, Member friend);
}
