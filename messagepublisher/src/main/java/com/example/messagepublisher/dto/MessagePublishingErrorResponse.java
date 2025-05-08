package com.example.messagepublisher.dto;

public class MessagePublishingErrorResponse extends BaseApiResponse {
    public MessagePublishingErrorResponse(String message) {
        super(false, message);
    }
}
