package com.example.messageconsumer.repository;

import com.example.messageconsumer.model.MessageDocument;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<MessageDocument, String> {

    List<MessageDocument> findByDestinationAndCreatedDateAfter(String destination, LocalDateTime after);
    List<MessageDocument> findByDestination(String destination);
}
