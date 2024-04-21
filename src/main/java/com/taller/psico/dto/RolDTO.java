package com.taller.psico.dto;

import java.io.Serializable;

public class RolDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer rolId;
    private String rolType;
    private boolean status;

    // Empty constructor
    public RolDTO() {
    }

    // Constructor with all fields
    public RolDTO(Integer rolId, String rolType, boolean status) {
        this.rolId = rolId;
        this.rolType = rolType;
        this.status = status;
    }

    // Getters and Setters
    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString method
    @Override
    public String toString() {
        return "RolDTO{" +
                "rolId=" + rolId +
                ", rolType='" + rolType + '\'' +
                ", status=" + status +
                '}';
    }
}
