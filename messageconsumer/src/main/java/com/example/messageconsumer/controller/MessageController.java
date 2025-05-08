package com.example.messageconsumer.controller;

import com.example.messageconsumer.service.MessageService;
import com.example.messageconsumer.dto.MessagesByDestinationResponse;
import com.example.messageconsumer.model.MessageDocument;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/destination/{destination}")
    public MessagesByDestinationResponse getMessagesByDestination(@PathVariable String destination) {
        List<MessageDocument> messages = messageService.getMessagesByDestination(destination);

        if (messages.isEmpty()) {
            return new MessagesByDestinationResponse("error", "No se encontraron mensajes para el destino: " + destination);
        }

        return new MessagesByDestinationResponse("success", "Consulta exitosa", messages);
    }
}
