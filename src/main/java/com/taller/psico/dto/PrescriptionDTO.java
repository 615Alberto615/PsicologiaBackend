package com.taller.psico.dto;

import java.io.Serializable;

public class PrescriptionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer prescriptionId;
    private String contentPrescription;
    private Integer clinicalAdvancesId;  // Only the ID of the clinical advances to simplify the DTO

    // Empty constructor
    public PrescriptionDTO() {
    }

    // Constructor with all fields
    public PrescriptionDTO(Integer prescriptionId, String contentPrescription, Integer clinicalAdvancesId) {
        this.prescriptionId = prescriptionId;
        this.contentPrescription = contentPrescription;
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    // Getters and Setters
    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getContentPrescription() {
        return contentPrescription;
    }

    public void setContentPrescription(String contentPrescription) {
        this.contentPrescription = contentPrescription;
    }

    public Integer getClinicalAdvancesId() {
        return clinicalAdvancesId;
    }

    public void setClinicalAdvancesId(Integer clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    // toString method
    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                "prescriptionId=" + prescriptionId +
                ", contentPrescription='" + contentPrescription + '\'' +
                ", clinicalAdvancesId=" + clinicalAdvancesId +
                '}';
    }
}
