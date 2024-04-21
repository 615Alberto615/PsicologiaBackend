package com.taller.psico.dto;

import java.io.Serializable;
import java.util.Date;

public class PostTreatmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer postTreatmentId;
    private Date endTreatment;
    private boolean reopeningCase;
    private String whyReopeningCase;
    private Integer treatmentId;  // Solo el ID del tratamiento asociado para simplificar

    // Constructor vacío
    public PostTreatmentDTO() {
    }

    // Constructor con todos los campos
    public PostTreatmentDTO(Integer postTreatmentId, Date endTreatment, boolean reopeningCase, String whyReopeningCase, Integer treatmentId) {
        this.postTreatmentId = postTreatmentId;
        this.endTreatment = endTreatment;
        this.reopeningCase = reopeningCase;
        this.whyReopeningCase = whyReopeningCase;
        this.treatmentId = treatmentId;
    }

    // Getters y Setters
    public Integer getPostTreatmentId() {
        return postTreatmentId;
    }

    public void setPostTreatmentId(Integer postTreatmentId) {
        this.postTreatmentId = postTreatmentId;
    }

    public Date getEndTreatment() {
        return endTreatment;
    }

    public void setEndTreatment(Date endTreatment) {
        this.endTreatment = endTreatment;
    }

    public boolean isReopeningCase() {
        return reopeningCase;
    }

    public void setReopeningCase(boolean reopeningCase) {
        this.reopeningCase = reopeningCase;
    }

    public String getWhyReopeningCase() {
        return whyReopeningCase;
    }

    public void setWhyReopeningCase(String whyReopeningCase) {
        this.whyReopeningCase = whyReopeningCase;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    // Método toString
    @Override
    public String toString() {
        return "PostTreatmentDTO{" +
                "postTreatmentId=" + postTreatmentId +
                ", endTreatment=" + endTreatment +
                ", reopeningCase=" + reopeningCase +
                ", whyReopeningCase='" + whyReopeningCase + '\'' +
                ", treatmentId=" + treatmentId +
                '}';
    }
}
