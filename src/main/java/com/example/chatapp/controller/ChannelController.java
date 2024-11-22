package com.example.chatapp.controller;s


import com.webchat.Seohyun.channel.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping
    public Channel createChannel(@RequestParam String name) {
        return channelService.createChannel(name);
    }

    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable Long id) {
        return channelService.getChannel(id).orElse(null);
    }
}
