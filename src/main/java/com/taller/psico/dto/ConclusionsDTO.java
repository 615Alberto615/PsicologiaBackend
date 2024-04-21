package com.taller.psico.dto;

import java.io.Serializable;

public class ConclusionsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer conclusionsId;
    private String content;
    private Integer postTreatmentId;  // Solo ID del tratamiento post, para simplificar el DTO

    // Constructor vacío
    public ConclusionsDTO() {
    }

    // Constructor con todos los campos
    public ConclusionsDTO(Integer conclusionsId, String content, Integer postTreatmentId) {
        this.conclusionsId = conclusionsId;
        this.content = content;
        this.postTreatmentId = postTreatmentId;
    }

    // Getters y Setters
    public Integer getConclusionsId() {
        return conclusionsId;
    }

    public void setConclusionsId(Integer conclusionsId) {
        this.conclusionsId = conclusionsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPostTreatmentId() {
        return postTreatmentId;
    }

    public void setPostTreatmentId(Integer postTreatmentId) {
        this.postTreatmentId = postTreatmentId;
    }

    // Método toString
    @Override
    public String toString() {
        return "ConclusionsDTO{" +
                "conclusionsId=" + conclusionsId +
                ", content='" + content + '\'' +
                ", postTreatmentId=" + postTreatmentId +
                '}';
    }
}
