package example.channel.repository;

import example.channel.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    // 메시지 조회 메서드 추가 필요 시 선언
}
