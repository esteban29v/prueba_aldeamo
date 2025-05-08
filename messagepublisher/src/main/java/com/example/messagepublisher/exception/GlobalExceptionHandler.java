package com.example.messagepublisher.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rabbitmq.client.RpcClient.Response;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.messagepublisher.dto.MessagePublishingErrorResponse;
import com.example.messagepublisher.dto.UnauthorizedLineResponse;
import com.example.messagepublisher.dto.ValidationErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UnauthorizedOriginException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<UnauthorizedLineResponse> handleUnauthorizedOriginException(UnauthorizedOriginException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new UnauthorizedLineResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(new ValidationErrorResponse("Error de validación", errors));
    }

    @ExceptionHandler(MessagePublishingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<MessagePublishingErrorResponse> handleMessagePublishingException(MessagePublishingException ex) {
        return ResponseEntity.badRequest().body(new MessagePublishingErrorResponse(ex.getMessage()));
    }
}