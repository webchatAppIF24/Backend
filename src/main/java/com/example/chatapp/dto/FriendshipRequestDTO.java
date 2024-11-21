package com.example.chatapp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FriendshipRequestDTO {
    private String friendLoginId; // 친구 요청을 보낸 사람의 로그인 ID
    private String friendName;    // 친구 요청을 보낸 사람의 이름
    private Long friendshipId;    // 친구 요청 ID (friendshipId)

    // 생성자
    public FriendshipRequestDTO(String friendLoginId, String friendName, Long friendshipId) {
        this.friendLoginId = friendLoginId;
        this.friendName = friendName;
        this.friendshipId = friendshipId;
    }
}