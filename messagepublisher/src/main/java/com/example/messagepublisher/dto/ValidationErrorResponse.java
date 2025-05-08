package com.example.messagepublisher.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

public class ValidationErrorResponse extends BaseApiResponse {
    public ValidationErrorResponse(String message, Map<String, String> errors) {
        super(false, message, errors);
    }
}
