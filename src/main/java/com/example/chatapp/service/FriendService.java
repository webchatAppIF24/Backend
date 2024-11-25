package com.example.chatapp.service;

import com.example.chatapp.domain.member.FriendRequestStatus;
import com.example.chatapp.domain.member.Member;
import com.example.chatapp.domain.member.MemberHasFriends;
import com.example.chatapp.dto.FriendDTO;
import com.example.chatapp.dto.FriendshipRequestDTO;
import com.example.chatapp.repository.MemberHasFriendsRepository;
import com.example.chatapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

    private final MemberRepository memberRepository;
    private final MemberHasFriendsRepository memberHasFriendsRepository;

    // 친구 요청 보내기
    public void handleAddFriend(String friendLoginId) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentUserIdLong = Long.parseLong(currentUserId);
        log.info("currentUserID {} -> friendId {} 친구 요청", currentUserIdLong, friendLoginId);
        Member currentUser = memberRepository.findById(currentUserIdLong)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자를 찾을 수 없습니다."));

        Member friend = memberRepository.findByLoginId(friendLoginId)
                .orElseThrow(() -> new IllegalArgumentException("친구를 찾을 수 없습니다."));

        if (isFriendAlready(currentUser, friend)) {
            throw new IllegalStateException("이미 친구입니다.");
        }

        MemberHasFriends friendship = new MemberHasFriends();
        friendship.setMember(currentUser);
        friendship.setFriend(friend);
        friendship.setStatus(FriendRequestStatus.PENDING); // 요청 중 상태로 설정

        memberHasFriendsRepository.save(friendship);
    }

    // 친구 요청 수락
    public void acceptFriendRequest(Long friendRequestId) {
        MemberHasFriends friendship = memberHasFriendsRepository.findById(friendRequestId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청을 찾을 수 없습니다."));

        if (friendship.getStatus() != FriendRequestStatus.PENDING) {
            throw new IllegalStateException("수락할 수 없는 상태입니다.");
        }

        friendship.setStatus(FriendRequestStatus.ACCEPTED);

        Member member = friendship.getMember();
        Member friend = friendship.getFriend();

        MemberHasFriends reciprocalFriendship = new MemberHasFriends();
        reciprocalFriendship.setMember(friend);
        reciprocalFriendship.setFriend(member);
        reciprocalFriendship.setStatus(FriendRequestStatus.ACCEPTED);

        memberHasFriendsRepository.save(friendship);
        memberHasFriendsRepository.save(reciprocalFriendship);
    }

    private boolean isFriendAlready(Member currentUser, Member friend) {
        return memberHasFriendsRepository.existsByMemberAndFriend(currentUser, friend) ||
                memberHasFriendsRepository.existsByMemberAndFriend(friend, currentUser);
    }

    //친구 리스트 가져오기
    public List<FriendDTO> getFriends(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return member.getFriendships().stream()
                .map(mhf -> {
                    Member friend = mhf.getFriend(); // 친구 객체
                    return new FriendDTO(friend.getLoginId(), friend.getName());
                })
                .collect(Collectors.toList());
    }

}
