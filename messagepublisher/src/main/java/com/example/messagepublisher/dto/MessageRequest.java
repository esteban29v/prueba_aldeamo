package com.example.messagepublisher.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MessageRequest {

	@NotBlank(message = "El número de origen es obligatorio")
	@Pattern(regexp = "^\\+?\\d{7,15}$", message = "El número de origen debe ser un número válido de teléfono")
	private String origin;

	@NotBlank(message = "El número de destino es obligatorio")
	@Pattern(regexp = "^\\+?\\d{7,15}$", message = "El número de destino debe ser un número válido de teléfono")
	private String destination;

    @AssertTrue(message = "El tipo de mensaje debe ser uno de los siguientes: Texto, Imagen, Video, Documento")
    private boolean isValidMessageType() {
        return MessageType.isValid(this.messageType);
    }

	@NotBlank(message = "El tipo de mensaje es obligatorio")
	private String messageType;


	@NotBlank(message = "El contenido del mensaje es obligatorio")
	private String content;
}
