package com.example.demo.handler;

import org.springframework.web.socket.CloseStatus;
import java.io.IOException;
import java.util.Iterator;
import org.springframework.web.socket.WebSocketMessage;
import com.example.demo.model.Message;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.concurrent.CopyOnWriteArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler
{
    private final ObjectMapper objectMapper;
    private final CopyOnWriteArrayList<WebSocketSession> sessions;

    public ChatWebSocketHandler() {
        this.objectMapper = new ObjectMapper();
        this.sessions = new CopyOnWriteArrayList<WebSocketSession>();
    }

    public void afterConnectionEstablished(final WebSocketSession session) {
        this.sessions.add(session);
        System.out.println("WebSocket \uc5f0\uacb0 \uc131\uacf5: " + session.getId());
    }

    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws IOException {
        final Message receivedMessage = (Message)this.objectMapper.readValue((String)message.getPayload(), (Class)Message.class);
        final String responseMessage = this.objectMapper.writeValueAsString((Object)receivedMessage);
        for (final WebSocketSession s : this.sessions) {
            if (s.isOpen()) {
                s.sendMessage((WebSocketMessage)new TextMessage((CharSequence)responseMessage));
            }
        }
    }

    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
        this.sessions.remove(session);
        System.out.println("WebSocket \uc5f0\uacb0 \uc885\ub8cc: " + session.getId());
    }
}
