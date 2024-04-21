package com.taller.psico.dto;

import java.io.Serializable;
import java.util.Date;

public class ClinicalAdvancesDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clinicalAdvancesId;
    private String descriptionAdvance;
    private Date dateAdvance;
    private String instructionGrade;
    private String companions;
    private String doctorDerivation;
    private String placeDerivation;
    private Date receptionDate;
    private boolean status;
    private String typeIntervation;
    private Integer treatmentId;  // Solo el ID del tratamiento asociado

    // Constructor vacío
    public ClinicalAdvancesDTO() {
    }

    // Constructor con todos los campos
    public ClinicalAdvancesDTO(Integer clinicalAdvancesId, String descriptionAdvance, Date dateAdvance, String instructionGrade,
                               String companions, String doctorDerivation, String placeDerivation, Date receptionDate,
                               boolean status, String typeIntervation, Integer treatmentId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
        this.descriptionAdvance = descriptionAdvance;
        this.dateAdvance = dateAdvance;
        this.instructionGrade = instructionGrade;
        this.companions = companions;
        this.doctorDerivation = doctorDerivation;
        this.placeDerivation = placeDerivation;
        this.receptionDate = receptionDate;
        this.status = status;
        this.typeIntervation = typeIntervation;
        this.treatmentId = treatmentId;
    }

    // Getters y Setters
    public Integer getClinicalAdvancesId() {
        return clinicalAdvancesId;
    }

    public void setClinicalAdvancesId(Integer clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    public String getDescriptionAdvance() {
        return descriptionAdvance;
    }

    public void setDescriptionAdvance(String descriptionAdvance) {
        this.descriptionAdvance = descriptionAdvance;
    }

    public Date getDateAdvance() {
        return dateAdvance;
    }

    public void setDateAdvance(Date dateAdvance) {
        this.dateAdvance = dateAdvance;
    }

    public String getInstructionGrade() {
        return instructionGrade;
    }

    public void setInstructionGrade(String instructionGrade) {
        this.instructionGrade = instructionGrade;
    }

    public String getCompanions() {
        return companions;
    }

    public void setCompanions(String companions) {
        this.companions = companions;
    }

    public String getDoctorDerivation() {
        return doctorDerivation;
    }

    public void setDoctorDerivation(String doctorDerivation) {
        this.doctorDerivation = doctorDerivation;
    }

    public String getPlaceDerivation() {
        return placeDerivation;
    }

    public void setPlaceDerivation(String placeDerivation) {
        this.placeDerivation = placeDerivation;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTypeIntervation() {
        return typeIntervation;
    }

    public void setTypeIntervation(String typeIntervation) {
        this.typeIntervation = typeIntervation;
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
        return "ClinicalAdvancesDTO{" +
                "clinicalAdvancesId=" + clinicalAdvancesId +
                ", descriptionAdvance='" + descriptionAdvance + '\'' +
                ", dateAdvance=" + dateAdvance +
                ", instructionGrade='" + instructionGrade + '\'' +
                ", companions='" + companions + '\'' +
                ", doctorDerivation='" + doctorDerivation + '\'' +
                ", placeDerivation='" + placeDerivation + '\'' +
                ", receptionDate=" + receptionDate +
                ", status=" + status +
                ", typeIntervation='" + typeIntervation + '\'' +
                ", treatmentId=" + treatmentId +
                '}';
    }
}
