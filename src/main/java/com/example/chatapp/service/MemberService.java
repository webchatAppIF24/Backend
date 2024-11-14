package com.example.chatapp.service;

import com.example.chatapp.domain.member.Member;
import com.example.chatapp.dto.JoinDTO;
import com.example.chatapp.dto.ResponseMessage;
import com.example.chatapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseMessage joinProcess(JoinDTO joinDTO) {
        String email = joinDTO.getEmail();
        String password = joinDTO.getPassword();
        String name = joinDTO.getName();
        String loginId = joinDTO.getLoginId();

        // 이메일 중복 확인
        if (memberRepository.existsByLoginId(loginId)) {
            log.warn("loginId already exists: {}", loginId);
            return new ResponseMessage("회원 가입 실패, 이미 존재하는 ID 입니다.");
        }

        // 비밀번호 암호화
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        // 새로운 Member 객체 생성
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encryptedPassword);
        member.setName(name);
        member.setLoginId(loginId);
        member.setRole("ROLE_USER"); // 기본 역할을 USER로 설정

        // 새로운 회원 저장
        memberRepository.save(member);

        return new ResponseMessage("회원 가입 성공");
    }
}
