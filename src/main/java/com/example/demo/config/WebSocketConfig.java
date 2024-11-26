package com.example.demo.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.example.demo.handler.ChatWebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer
{
    private final ChatWebSocketHandler chatWebSocketHandler;

    public WebSocketConfig(final ChatWebSocketHandler chatWebSocketHandler) {
        this.chatWebSocketHandler = chatWebSocketHandler;
    }

    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler((WebSocketHandler)this.chatWebSocketHandler, new String[] { "/ws/private-chat" }).setAllowedOrigins(new String[] { "*" });
    }
}

