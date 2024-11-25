package com.example.chatapp.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnector {

    private MongoDatabase database;

    // DB 연결 메서드
    public MongoDBConnector() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb+srv://gywns0609:v2fDNq17pCyAoKhE@cluster0.zij2a.mongodb.net/WebChatting?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("WebChatting"); // 웹 채팅용 DB 이름
            System.out.println("DB Connecting..");
        } catch (Exception e) {
            System.out.println("MongoDB 연결에 실패했습니다: " + e.getMessage());
        }
    }

    // 컬렉션을 가져오는 메서드
    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    // 채팅 내역을 DB에 저장하는 메서드
    public void saveChatHistory(String sender, String receiver, String message) {
        MongoCollection<Document> collection = getCollection("chatHistory");

        Document chatDocument = new Document("sender", sender)//보내는 사람 id
                .append("receiver", receiver)//받는 사람 id
                .append("message", message)//메세지 내용
                .append("timestamp", System.currentTimeMillis());

        collection.insertOne(chatDocument);
        System.out.println("채팅 내역이 MongoDB에 저장되었습니다: " + chatDocument.toJson());
    }

    // 게시글을 DB에 저장하는 메서드
    public void savePost(String author, String title, String content) {
        MongoCollection<Document> collection = getCollection("posts");

        Document postDocument = new Document("author", author)//작성자 id
                .append("title", title)//게시물 제목
                .append("content", content)//게시물 내용
                .append("date", System.currentTimeMillis());

        collection.insertOne(postDocument);
        System.out.println("게시글이 MongoDB에 저장되었습니다: " + postDocument.toJson());
    }
}
