package com.example.messageconsumer.service;

import com.example.messageconsumer.exception.MessageProcessingException;
import com.example.messageconsumer.model.MessageDocument;
import com.example.messageconsumer.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void processIncomingMessage(Map<String, String> messageMap, long receivedTimestamp) {
		try {
			String destination = messageMap.get("destination");

            LocalDateTime now = LocalDateTime.now();
			List<MessageDocument> recentMessages = messageRepository.findByDestinationAndCreatedDateAfter(
					destination, now.minusHours(24)
			);
	
			MessageDocument document = new MessageDocument();
			document.setOrigin(messageMap.get("origin"));
			document.setDestination(destination);
			document.setMessageType(messageMap.get("messageType"));
			document.setContent(messageMap.get("content"));
			document.setCreatedDate(now);
	
			if (recentMessages.size() >= 3) {
				document.setError("Límite de 3 mensajes en 24 horas alcanzado para este destinatario");
				logger.warn("Límite alcanzado para destino: {}", destination);
			}
	
			long processingTime = System.currentTimeMillis() - receivedTimestamp;
			document.setProcessingTime(processingTime);
	
			try {
				messageRepository.save(document);
			} catch (Exception e) {
				throw new MessageProcessingException("Error al guardar el mensaje en la base de datos", e);
			}
		} catch (Exception e) {
			throw new MessageProcessingException("Error al procesar el mensaje", e);
		}
	}

	public List<MessageDocument> getMessagesByDestination(String destination) {
        return messageRepository.findByDestination(destination);
    }
}
