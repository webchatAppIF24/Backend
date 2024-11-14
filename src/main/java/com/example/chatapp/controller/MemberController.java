package com.example.chatapp.controller;

import com.example.chatapp.dto.JoinDTO;
import com.example.chatapp.dto.ResponseMessage;
import com.example.chatapp.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@ResponseBody
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<ResponseMessage> signup(@RequestBody @Valid JoinDTO joinDTO) {

        log.info("{} 회원가입 시도", joinDTO.getName());
        ResponseMessage responseMessage = memberService.joinProcess(joinDTO);

        if (responseMessage.getMessage().startsWith("회원 가입 실패")) {
            log.info("{} 회원가입 실패, 중복 사용자", joinDTO.getName());
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

        log.info("{} 회원가입 성공", joinDTO.getName());
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }


}
