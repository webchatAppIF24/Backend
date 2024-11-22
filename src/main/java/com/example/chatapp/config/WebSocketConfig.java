package com.example.chatapp.config;

import ./config.YourWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new YourWebSocketHandler(), "/ws") // 웹소켓 핸들러 등록
                .setAllowedOrigins("*"); // 모든 출처에서의 요청 허용
    }
}
