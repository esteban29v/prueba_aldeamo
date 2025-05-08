package com.example.messageconsumer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
public class MessageDocument {

    @Id
    private String id;

    private String origin;
    private String destination;
    private String messageType;
    private String content;

    private long processingTime;
    private LocalDateTime createdDate;
    private String error;
}