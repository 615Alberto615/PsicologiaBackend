package com.taller.psico.dto;

import java.io.Serializable;

public class OfficeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer officeId;
    private String address;
    private boolean status;

    // Constructor vacío
    public OfficeDTO() {
    }

    // Constructor con todos los campos
    public OfficeDTO(Integer officeId, String address, boolean status) {
        this.officeId = officeId;
        this.address = address;
        this.status = status;
    }

    // Getters y Setters
    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Método toString
    @Override
    public String toString() {
        return "OfficeDTO{" +
                "officeId=" + officeId +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
