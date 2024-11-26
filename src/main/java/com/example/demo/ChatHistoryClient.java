package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.controller.ChatHistoryRequest;

public class ChatHistoryClient {
    private JFrame frame;
    private JTextArea chatArea;
    private JButton loadHistoryButton;
    private final String token = "your_authorization_token"; // 실제 토큰으로 교체 필요

    public ChatHistoryClient() {
        frame = new JFrame("Chat History");
        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        loadHistoryButton = new JButton("Load Chat History");

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        frame.add(loadHistoryButton, BorderLayout.SOUTH);

        loadHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadChatHistory();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadChatHistory() {
        String url = "http://localhost:4001/api/private-chat/history"; // 실제 서버 URL로 변경 필요

        // ChatHistoryRequest 객체 생성 (userId와 friendId를 실제 값으로 설정)
        ChatHistoryRequest requestObj = new ChatHistoryRequest("userId", "friendId");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestObj);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                chatArea.setText(response.body());
            } else {
                chatArea.setText("Failed to load chat history.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            chatArea.setText("Error loading chat history.");
        }
    }

    public static void main(String[] args) {
        new ChatHistoryClient();
    }
}
