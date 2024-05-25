package com.taller.psico.dto;

public class CompletionDto {
    private String sessionId;
    private String prompt;
    private String completion;

    public CompletionDto() {
    }

    public CompletionDto(String sessionId, String prompt, String completion) {
        this.sessionId = sessionId;
        this.prompt = prompt;
        this.completion = completion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        return "CompletionDto{" +
                "sessionId='" + sessionId + '\'' +
                ", prompt='" + prompt + '\'' +
                ", completion='" + completion + '\'' +
                '}';
    }
}