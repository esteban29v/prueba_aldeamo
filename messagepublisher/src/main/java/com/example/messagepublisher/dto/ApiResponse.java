package com.example.messagepublisher.dto;

public interface ApiResponse {
    boolean isStatus();
    String getMessage();
    
    default boolean hasErrors() {
        return !isStatus();
    }
}