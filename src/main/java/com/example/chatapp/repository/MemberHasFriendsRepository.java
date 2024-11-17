package com.example.chatapp.repository;

import com.example.chatapp.domain.member.FriendRequestStatus;
import com.example.chatapp.domain.member.Member;
import com.example.chatapp.domain.member.MemberHasFriends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberHasFriendsRepository extends JpaRepository<MemberHasFriends, Long> {

    boolean existsByMemberAndFriend(Member currentUser, Member friend);

    List<MemberHasFriends> findByFriendAndStatus(Member member, FriendRequestStatus friendRequestStatus);
}
