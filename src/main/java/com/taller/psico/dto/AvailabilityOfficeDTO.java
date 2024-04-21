package com.taller.psico.dto;

import java.io.Serializable;

public class AvailabilityOfficeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer availabilityOfficeId;
    private Integer availabilityId;  // Solo ID de la disponibilidad
    private Integer officeId;        // Solo ID de la oficina

    // Constructor vacío
    public AvailabilityOfficeDTO() {
    }

    // Constructor con todos los campos
    public AvailabilityOfficeDTO(Integer availabilityOfficeId, Integer availabilityId, Integer officeId) {
        this.availabilityOfficeId = availabilityOfficeId;
        this.availabilityId = availabilityId;
        this.officeId = officeId;
    }

    // Getters y Setters
    public Integer getAvailabilityOfficeId() {
        return availabilityOfficeId;
    }

    public void setAvailabilityOfficeId(Integer availabilityOfficeId) {
        this.availabilityOfficeId = availabilityOfficeId;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    // Método toString
    @Override
    public String toString() {
        return "AvailabilityOfficeDTO{" +
                "availabilityOfficeId=" + availabilityOfficeId +
                ", availabilityId=" + availabilityId +
                ", officeId=" + officeId +
                '}';
    }
}
