package com.example.messagepublisher.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public abstract class BaseApiResponse implements ApiResponse {
    private boolean status;
    private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public BaseApiResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

	public BaseApiResponse(boolean status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
		this.errors = errors;
    }
    
    @Override
    public boolean isStatus() {
        return status;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}