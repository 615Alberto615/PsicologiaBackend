package com.taller.psico.dto;

public class ChatMessageDto {
    private String sessionId;
    private String message;
    private String response;

    public ChatMessageDto(String sessionId, String message, String response) {
            this.sessionId = sessionId;
            this.message = message;
            this.response = response;
    }

    public ChatMessageDto() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
