package com.example.messageconsumer.consumer;

import com.example.messageconsumer.exception.MessageProcessingException;
import com.example.messageconsumer.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class MessageConsumer {

    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    public MessageConsumer(ObjectMapper objectMapper, MessageService messageService) {
        this.objectMapper = objectMapper;
        this.messageService = messageService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}", ackMode = "MANUAL")
    public void handleMessage(String payload, @Header(AmqpHeaders.TIMESTAMP) Long receivedTimestamp, Channel channel, Message message) {
        try {
            Map<String, String> messageMap = objectMapper.readValue(payload, Map.class);
            messageService.processIncomingMessage(messageMap, receivedTimestamp);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (MessageProcessingException e) {
            logger.error("Error al procesar el mensaje recibido: {}", e.getMessage(), e);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ioException) {
                logger.error("Error al intentar rechazar el mensaje en la cola", ioException);
            }
        } catch (Exception e) {
            logger.error("Error inesperado al procesar el mensaje recibido", e);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException ioException) {
                logger.error("Error al intentar rechazar el mensaje en la cola", ioException);
            }
        }
    }
}