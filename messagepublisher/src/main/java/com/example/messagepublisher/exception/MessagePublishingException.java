package com.example.messagepublisher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MessagePublishingException extends RuntimeException {
    public MessagePublishingException(String reason, Throwable cause) {
        super("Error al publicar mensaje en RabbitMQ: " + reason, cause);
    }
}
