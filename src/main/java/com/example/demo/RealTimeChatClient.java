package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class RealTimeChatClient {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private WebSocketSession session;

    public RealTimeChatClient() {
        frame = new JFrame("Real-Time Chat");
        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        messageField = new JTextField(40);
        sendButton = new JButton("Send");

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(messageField);
        panel.add(sendButton);
        frame.add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    messageField.setText("");
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        String url = "ws://localhost:4001/ws/private-chat";

        StandardWebSocketClient client = new StandardWebSocketClient();
        client.doHandshake(new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                RealTimeChatClient.this.session = session;
                chatArea.append("Connected to the chat server\n");
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                chatArea.append("Server: " + message.getPayload() + "\n");
            }

            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                chatArea.append("Error: " + exception.getMessage() + "\n");
                exception.printStackTrace();
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                chatArea.append("Disconnected from the chat server\n");
            }
        }, url);
    }

    // sendMessage 메서드 추가
    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            try {
                // JSON 형식으로 content 필드를 포함한 메시지 생성
            	String jsonMessage = String.format("{\"sender\": \"%s\", \"receiver\": \"%s\", \"content\": \"%s\", \"timestamp\": \"%s\"}",
                        "Client", "Server", message, System.currentTimeMillis());

                session.sendMessage(new TextMessage(jsonMessage));
                chatArea.append("You: " + message + "\n");
            } catch (Exception ex) {
                ex.printStackTrace();
                chatArea.append("Error sending message\n");
            }
        } else {
            chatArea.append("Not connected to the server\n");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RealTimeChatClient());
    }
}
