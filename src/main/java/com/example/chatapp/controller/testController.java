package com.example.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class testController {

    @GetMapping("/")
    public String method() {
        return "testttest";
    }

    @GetMapping("/chat")
    public String testChat(){
        return "aaaaaaaaa";
    }
}
