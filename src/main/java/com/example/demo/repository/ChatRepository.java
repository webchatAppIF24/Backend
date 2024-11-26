package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Message, String>
{
    List<Message> findBySenderAndReceiverOrReceiverAndSender(final String p0, final String p1, final String p2, final String p3);
}
