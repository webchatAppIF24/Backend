package com.example.chatapp.security;

import com.example.chatapp.config.WhiteListUrls;
import com.example.chatapp.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private static final List<String> EXCLUDED_URLS = WhiteListUrls.WHITELIST_PATHS;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 요청 URI 로그 출력
        log.debug("요청 URI: {}", requestURI);

        // Whitelist 경로는 필터링을 우회
        if (isWhiteList(requestURI)) {
            log.debug("화이트리스트 경로 우회: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // JWT 처리
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.debug("Authorization 헤더가 없음: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        if (jwtUtil.isExpired(token)) {
            log.warn("토큰이 만료됨: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = jwtUtil.getUserId(token);
        String role = jwtUtil.getRole(token);

        CustomUserDetails customUserDetails = new CustomUserDetails(userId, role, "aaaaa");

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.debug("JWT 인증 완료: {} - {}", userId, role);

        filterChain.doFilter(request, response);
    }


    // 요청 URI가 화이트리스트인지 확인
    private boolean isWhiteList(String uri) {
        return EXCLUDED_URLS.stream().anyMatch(pattern -> pathMatcher.match(pattern, uri));
    }
}
