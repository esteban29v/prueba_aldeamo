package com.example.messagepublisher.dto;

public class UnauthorizedLineResponse extends BaseApiResponse {
	public UnauthorizedLineResponse(String message) {
		super(false, message);
	}
}
