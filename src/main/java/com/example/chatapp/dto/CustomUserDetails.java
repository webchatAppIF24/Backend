package com.example.chatapp.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {


    private Long id;
    private String role;
    private String password;

    public CustomUserDetails(Long id, String role,String password) {
        this.id = id;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> role); // 역할 정보 추가
        return authorities;
    }

    @Override
    public String getPassword() {
        return password; // 패스워드는 필요없으므로 null 반환
    }

    @Override
    public String getUsername() {
        return String.valueOf(id); // 이메일이 아닌 ID로 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
