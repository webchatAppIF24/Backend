package com.example.chatapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    // getters and setters
}