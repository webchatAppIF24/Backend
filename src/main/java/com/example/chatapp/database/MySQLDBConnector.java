package com.example.chatapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBConnector {

    private Connection connection;

    // MySQL 데이터베이스 연결 설정
    public MySQLDBConnector() {
        String url = "jdbc:mysql://127.0.0.1:3306/WebChattingDB"; //WebChatting 데이터베이스 url
        String user = "root"; // MySQL 사용자 이름
        String password = "1234"; // MySQL 비밀번호

        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB 연결
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL DB 연결에 성공했습니다.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("MySQL DB 연결에 실패했습니다: " + e.getMessage());
        }
    }

    // DB 연결 반환 메서드
    public Connection getConnection() {
        return connection;
    }

    // 연결 종료 메서드
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("MySQL DB 연결이 종료되었습니다.");
            } catch (SQLException e) {
                System.out.println("MySQL DB 연결 종료 중 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
}
