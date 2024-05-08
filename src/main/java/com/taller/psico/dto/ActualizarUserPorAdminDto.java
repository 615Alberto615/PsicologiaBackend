package com.taller.psico.dto;

public class ActualizarUserPorAdminDto {

    private Integer userId;
    private Boolean status;
    private String rolType;

    public ActualizarUserPorAdminDto() {
    }

    public ActualizarUserPorAdminDto(Integer userId, Boolean status, String rolType) {
        this.userId = userId;
        this.status = status;
        this.rolType = rolType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }

    @Override
    public String toString() {
        return "ActualizarUserPorAdminDto{" +
                "userId=" + userId +
                ", status=" + status +
                ", rolType='" + rolType + '\'' +
                '}';
    }
}
