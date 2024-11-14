package com.example.chatapp.security;

import com.example.chatapp.domain.member.Member;
import com.example.chatapp.dto.CustomUserDetails;
import com.example.chatapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findByLoginId(userId);

        Member member = optionalMember.orElseThrow(() -> {
            log.warn("유저를 찾을 수 없음, 아이디: {}", userId); // 로그를 남김
            return new UsernameNotFoundException("User not found with loginId: " + userId);
        });

        // CustomUserDetails를 반환
        return new CustomUserDetails(member.getId(), member.getRole(),member.getPassword());
    }
}
