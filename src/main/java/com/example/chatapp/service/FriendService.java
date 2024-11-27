package com.example.chatapp.service;

import com.example.chatapp.domain.member.Member;
import com.example.chatapp.domain.member.MemberHasFriends;
import com.example.chatapp.dto.FriendDTO;
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

    //친구 추가
    public void handleAddFriend(String friendLoginId) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        Long currentUserIdLong = Long.parseLong(currentUserId);

        log.info("currentUserID {} -> friendId {} 친구 요청", currentUserIdLong, friendLoginId);

        // 현재 사용자와 친구로 추가할 상대방 조회
        Member currentUser = memberRepository.findById(currentUserIdLong)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자를 찾을 수 없습니다."));
        Member friend = memberRepository.findByLoginId(friendLoginId)
                .orElseThrow(() -> new IllegalArgumentException("친구를 찾을 수 없습니다."));

        // 이미 친구인지 확인
        if (isFriendAlready(currentUser, friend)) {
            throw new IllegalStateException("이미 친구로 등록되어 있습니다.");
        }

        // 현재 사용자 → 상대방 방향의 관계만 저장
        MemberHasFriends friendship = new MemberHasFriends();
        friendship.setMember(currentUser); // 요청 보낸 사용자
        friendship.setFriend(friend);     // 요청받은 사용자

        memberHasFriendsRepository.save(friendship);
    }

    //이미 친구 인지 확인
    private boolean isFriendAlready(Member user, Member potentialFriend) {
        return memberHasFriendsRepository.existsByMemberAndFriend(user, potentialFriend);
    }

    //친구 리스트
    public List<FriendDTO> getFriends(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 요청을 보낸 대상(friend)들만 가져오기
        return member.getFriendships().stream()
                .map(mhf -> {
                    Member friend = mhf.getFriend(); // 친구 객체
                    return new FriendDTO(friend.getLoginId(), friend.getName());
                })
                .collect(Collectors.toList());
    }

}
