package com.example.messagepublisher.service;

import com.example.messagepublisher.exception.UnauthorizedOriginException;
import com.example.messagepublisher.repository.AuthorizedLineRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final AuthorizedLineRepository authorizedLineRepository;

    public AuthorizationService(AuthorizedLineRepository authorizedLineRepository) {
        this.authorizedLineRepository = authorizedLineRepository;
    }

    public boolean isOriginAuthorized(String origin) {
        if (!authorizedLineRepository.existsByOriginAndIsActiveTrue(origin)) {
            throw new UnauthorizedOriginException(origin);
        }
        return true;
    }
}