package com.example.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class MySQLDBConfig {

    private Connection connection;

    @Bean
    public Connection mySQLConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/WebChattingDB";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 연결에 성공했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("MySQL 연결에 실패했습니다: " + e.getMessage());
        }
        return connection;
    }

    // 연결 종료 메서드 (연결 종료 시 수동 호출)
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("MySQL 연결이 종료되었습니다.");
            } catch (SQLException e) {
                System.out.println("MySQL 연결 종료 중 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
}
