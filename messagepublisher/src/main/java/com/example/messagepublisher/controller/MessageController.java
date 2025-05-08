package com.example.messagepublisher.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.messagepublisher.dto.MessageRequest;
import com.example.messagepublisher.dto.MessageResponse;
import com.example.messagepublisher.service.AuthorizationService;
import com.example.messagepublisher.service.MessagePublisherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final AuthorizationService authorizationService;
    private final MessagePublisherService messagePublisherService;

    public MessageController(AuthorizationService authorizationService,
                             MessagePublisherService messagePublisherService) {
        this.authorizationService = authorizationService;
        this.messagePublisherService = messagePublisherService;
    }

    @PostMapping("/publish")
    public ResponseEntity<MessageResponse> publishMessage(@RequestBody @Valid MessageRequest request) {

        authorizationService.isOriginAuthorized(request.getOrigin());

        messagePublisherService.publishMessage(request);

        return ResponseEntity.ok(new MessageResponse(true, "Mensaje enviado correctamente"));
    }
}
