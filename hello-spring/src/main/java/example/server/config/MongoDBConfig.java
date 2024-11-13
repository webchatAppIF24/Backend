package example.server.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    private MongoDatabase database;

    // MongoDB 설정을 통해 DB 연결
    @Bean
    public MongoDatabase mongoDatabase() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb+srv://gywns0609:v2fDNq17pCyAoKhE@cluster0.zij2a.mongodb.net/WebChatting?retryWrites=true&w=majority");
            MongoClient mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("WebChatting"); // 웹 채팅용 DB 이름
            System.out.println("MongoDB 연결되었습니다.");
        } catch (Exception e) {
            System.out.println("MongoDB 연결에 실패했습니다: " + e.getMessage());
        }
        return database;
    }

    // MongoDB 컬렉션 가져오기 메서드
    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
