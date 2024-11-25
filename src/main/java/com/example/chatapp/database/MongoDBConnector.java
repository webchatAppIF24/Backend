package com.example.chatapp.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnector {

    private final MongoDatabase database;

    // MongoDB 연결 메서드
    public MongoDBConnector() {
        try {
            ConnectionString connectionString = new ConnectionString("mongodb+srv://gywns0609:v2fDNq17pCyAoKhE@cluster0.zij2a.mongodb.net/WebChatting?retryWrites=true&w=majority");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();

            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("WebChatting");
            System.out.println("MongoDB 연결 성공: " + database.getName());
        } catch (Exception e) {
            throw new RuntimeException("MongoDB 연결에 실패했습니다: " + e.getMessage(), e);
        }
    }

    // 컬렉션을 가져오는 메서드
    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    // 채팅 내역 저장
    public void saveChatHistory(String sender, String receiver, String message) {
        MongoCollection<Document> collection = getCollection("chatHistory");

        Document chatDocument = new Document("sender", sender)
                .append("receiver", receiver)
                .append("message", message)
                .append("timestamp", System.currentTimeMillis());

        collection.insertOne(chatDocument);
        System.out.println("채팅 내역 저장 완료: " + chatDocument.toJson());
    }

    // 게시글 저장
    public void savePost(String author, String title, String content) {
        MongoCollection<Document> collection = getCollection("posts");

        Document postDocument = new Document("author", author)
                .append("title", title)
                .append("content", content)
                .append("date", System.currentTimeMillis());

        collection.insertOne(postDocument);
        System.out.println("게시글 저장 완료: " + postDocument.toJson());
    }
}
