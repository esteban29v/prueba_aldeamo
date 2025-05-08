package com.example.messagepublisher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedOriginException extends RuntimeException {
    public UnauthorizedOriginException(String origin) {
        super(String.format("El número de origen '%s' no está autorizado", origin));
    }
}