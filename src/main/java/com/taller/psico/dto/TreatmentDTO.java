package com.taller.psico.dto;

import java.io.Serializable;
import java.util.Date;

public class TreatmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer treatmentId;
    private String name;
    private Date startDate;
    private boolean status;
    private Integer userPatientId;  // Only the ID of the patient user
    private Integer userPsychiatristId;  // Only the ID of the psychiatrist user

    // Empty constructor
    public TreatmentDTO() {
    }

    // Constructor with all fields
    public TreatmentDTO(Integer treatmentId, String name, Date startDate, boolean status, Integer userPatientId, Integer userPsychiatristId) {
        this.treatmentId = treatmentId;
        this.name = name;
        this.startDate = startDate;
        this.status = status;
        this.userPatientId = userPatientId;
        this.userPsychiatristId = userPsychiatristId;
    }

    // Getters and Setters
    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(Integer userPatientId) {
        this.userPatientId = userPatientId;
    }

    public Integer getUserPsychiatristId() {
        return userPsychiatristId;
    }

    public void setUserPsychiatristId(Integer userPsychiatristId) {
        this.userPsychiatristId = userPsychiatristId;
    }

    // toString method
    @Override
    public String toString() {
        return "TreatmentDTO{" +
                "treatmentId=" + treatmentId +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", status=" + status +
                ", userPatientId=" + userPatientId +
                ", userPsychiatristId=" + userPsychiatristId +
                '}';
    }
}
