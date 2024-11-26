package com.example.demo.controller;

public class ChatHistoryRequest {
    private String friendId;
    private String userId;

    // 기본 생성자 추가
    public ChatHistoryRequest() {
    }

    // friendId와 userId를 매개변수로 받는 생성자
    public ChatHistoryRequest(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getFriendId() {
        return this.friendId;
    }

    public void setFriendId(final String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }
}
