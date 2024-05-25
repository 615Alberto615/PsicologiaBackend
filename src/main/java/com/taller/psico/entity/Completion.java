package com.taller.psico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "completions")
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "prompt")
    private String prompt;

    @Column(name = "completion", length = 1000)
    private String completion;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Completion() {
    }

    public Completion(Long id, String sessionId, String prompt, String completion, LocalDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.prompt = prompt;
        this.completion = completion;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Completion{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", prompt='" + prompt + '\'' +
                ", completion='" + completion + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
