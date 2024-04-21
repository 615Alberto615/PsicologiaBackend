package com.taller.psico.dto;

import java.io.Serializable;

public class ObservationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer observationId;
    private String observationContent;
    private Integer clinicalAdvancesId;  // Solo el ID de avances clínicos para mantenerlo simple y evitar cargas pesadas

    // Constructor vacío
    public ObservationDTO() {
    }

    // Constructor con todos los campos
    public ObservationDTO(Integer observationId, String observationContent, Integer clinicalAdvancesId) {
        this.observationId = observationId;
        this.observationContent = observationContent;
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    // Getters y Setters
    public Integer getObservationId() {
        return observationId;
    }

    public void setObservationId(Integer observationId) {
        this.observationId = observationId;
    }

    public String getObservationContent() {
        return observationContent;
    }

    public void setObservationContent(String observationContent) {
        this.observationContent = observationContent;
    }

    public Integer getClinicalAdvancesId() {
        return clinicalAdvancesId;
    }

    public void setClinicalAdvancesId(Integer clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    // Método toString
    @Override
    public String toString() {
        return "ObservationDTO{" +
                "observationId=" + observationId +
                ", observationContent='" + observationContent + '\'' +
                ", clinicalAdvancesId=" + clinicalAdvancesId +
                '}';
    }
}
