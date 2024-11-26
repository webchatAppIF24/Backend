package com.example.chatapp.config;

import com.example.chatapp.database.MongoDBConnector;
import com.example.chatapp.database.MySQLDBConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    // MySQL 커넥터를 Bean으로 등록
    @Bean
    public MySQLDBConnector mySQLDBConnector() {
        return new MySQLDBConnector();
    }

    // MongoDB 커넥터를 Bean으로 등록
    @Bean
    public MongoDBConnector mongoDBConnector() {
        return new MongoDBConnector();
    }
}
