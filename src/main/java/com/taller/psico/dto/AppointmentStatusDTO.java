package com.taller.psico.dto;

import java.io.Serializable;

public class AppointmentStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer appointmentStatusId;
    private String nameStatus;
    private boolean status;

    // Constructor vacío
    public AppointmentStatusDTO() {
    }

    // Constructor con todos los campos
    public AppointmentStatusDTO(Integer appointmentStatusId, String nameStatus, boolean status) {
        this.appointmentStatusId = appointmentStatusId;
        this.nameStatus = nameStatus;
        this.status = status;
    }

    // Getters y Setters
    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método toString
    @Override
    public String toString() {
        return "AppointmentStatusDTO{" +
                "appointmentStatusId=" + appointmentStatusId +
                ", nameStatus='" + nameStatus + '\'' +
                ", status=" + status +
                '}';
    }
}
