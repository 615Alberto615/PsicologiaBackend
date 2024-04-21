package com.taller.psico.dto;

import java.io.Serializable;

public class TypeNotificationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer typeNotificationId;
    private int type;

    // Empty constructor
    public TypeNotificationDTO() {
    }

    // Constructor with all fields
    public TypeNotificationDTO(Integer typeNotificationId, int type) {
        this.typeNotificationId = typeNotificationId;
        this.type = type;
    }

    // Getters and Setters
    public Integer getTypeNotificationId() {
        return typeNotificationId;
    }

    public void setTypeNotificationId(Integer typeNotificationId) {
        this.typeNotificationId = typeNotificationId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    // toString method
    @Override
    public String toString() {
        return "TypeNotificationDTO{" +
                "typeNotificationId=" + typeNotificationId +
                ", type=" + type +
                '}';
    }
}
