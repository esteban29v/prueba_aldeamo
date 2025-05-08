package com.example.messagepublisher.dto;

public class MessageResponse extends BaseApiResponse {
	public MessageResponse(boolean status, String message) {
        super(status, message);
    }
}
