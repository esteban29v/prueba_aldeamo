package com.example.messagepublisher.service;

import java.time.Instant;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.messagepublisher.dto.MessageRequest;
import com.example.messagepublisher.exception.MessagePublishingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.support.AmqpHeaders;

@Service
public class MessagePublisherService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final String exchangeName;
    private final String routingKey;

    public MessagePublisherService(
        RabbitTemplate rabbitTemplate, 
        ObjectMapper objectMapper, 
        @Value("${app.rabbitmq.exchange}") String exchangeName,
        @Value("${app.rabbitmq.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void publishMessage(MessageRequest request) {
        try {
            byte[] messageBody = objectMapper.writeValueAsBytes(request);

            Message message = MessageBuilder
                    .withBody(messageBody)
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .setHeader(AmqpHeaders.TIMESTAMP, Instant.now().toEpochMilli())
                    .build();

            rabbitTemplate.send(
                    exchangeName,
                    routingKey,
                    message
            );
        } catch (Exception e) {
            throw new MessagePublishingException(e.getMessage(), e);
        }
    }
}
