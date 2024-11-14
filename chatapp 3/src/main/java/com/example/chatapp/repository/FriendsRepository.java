package com.example.chatapp.repository;

import com.example.chatapp.domain.member.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

}
