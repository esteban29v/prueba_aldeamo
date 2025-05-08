package com.example.messageconsumer.dto;

import com.example.messageconsumer.model.MessageDocument;
import lombok.Data;

import java.util.List;

@Data
public class MessagesByDestinationResponse {

    private String status;
    private String message;
    private List<MessageDocument> messages;  
    private int totalMessages;                 

    public MessagesByDestinationResponse(String status, String message, List<MessageDocument> messages) {
        this.status = status;
        this.message = message;
        this.totalMessages = messages != null ? messages.size() : 0;
        this.messages = messages;
    }

    public MessagesByDestinationResponse(String status, String message) {
        this.status = status;
        this.message = message;
        this.totalMessages = 0;
        this.messages = null;
    }
}
