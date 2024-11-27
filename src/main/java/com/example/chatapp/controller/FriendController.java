package com.example.chatapp.controller;

import com.example.chatapp.domain.member.Member;
import com.example.chatapp.dto.FriendDTO;
import com.example.chatapp.dto.FriendRequestDTO;
import com.example.chatapp.dto.FriendshipRequestDTO;
import com.example.chatapp.repository.MemberRepository;
import com.example.chatapp.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final MemberRepository memberRepository;

    //친구 추가 요청
    @PostMapping("/add")
    public ResponseEntity<String> addFriend(@RequestBody FriendRequestDTO friendRequestDTO) {
        try {
            log.info("friendLoinId = {}", friendRequestDTO.getFriendLoginId());
            friendService.handleAddFriend(friendRequestDTO.getFriendLoginId());
            return ResponseEntity.ok("친구 추가 요청이 전송되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    //친구 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<FriendDTO>> getFriends() {
        Long currentUserId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FriendDTO> friends = friendService.getFriends(currentUserId);
        return ResponseEntity.ok(friends);
    }

}