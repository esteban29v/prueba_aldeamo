package com.example.messagepublisher.dto;

public enum MessageType {
    TEXTO("Texto"),
    IMAGEN("Imagen"),
    VIDEO("Video"),
    DOCUMENTO("Documento");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValid(String value) {
        for (MessageType type : MessageType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}