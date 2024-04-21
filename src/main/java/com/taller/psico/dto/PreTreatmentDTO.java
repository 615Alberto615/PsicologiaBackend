package com.taller.psico.dto;

import java.io.Serializable;

public class PreTreatmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer preTreatmentId;
    private Integer treatmentId;  // Only the ID of the treatment to simplify the DTO
    private Integer userId;       // Only the ID of the user involved in the pre-treatment

    // Empty constructor
    public PreTreatmentDTO() {
    }

    // Constructor with all fields
    public PreTreatmentDTO(Integer preTreatmentId, Integer treatmentId, Integer userId) {
        this.preTreatmentId = preTreatmentId;
        this.treatmentId = treatmentId;
        this.userId = userId;
    }

    // Getters and Setters
    public Integer getPreTreatmentId() {
        return preTreatmentId;
    }

    public void setPreTreatmentId(Integer preTreatmentId) {
        this.preTreatmentId = preTreatmentId;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // toString method
    @Override
    public String toString() {
        return "PreTreatmentDTO{" +
                "preTreatmentId=" + preTreatmentId +
                ", treatmentId=" + treatmentId +
                ", userId=" + userId +
                '}';
    }
}
