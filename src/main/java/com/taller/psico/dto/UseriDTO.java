package com.taller.psico.dto;

import java.io.Serializable;

public class UseriDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userName;
    private String password;  // Consider encryption or hashing in real scenarios
    private boolean status;
    private Integer peopleId; // Only the ID of the associated People entity
    private Integer rolId;    // Only the ID of the associated Rol entity

    // Empty constructor
    public UseriDTO() {
    }

    // Constructor with all fields
    public UseriDTO(Integer userId, String userName, String password, boolean status, Integer peopleId, Integer rolId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.peopleId = peopleId;
        this.rolId = rolId;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    // toString method
    @Override
    public String toString() {
        return "UseriDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +  // Displaying password is generally not advised
                ", status=" + status +
                ", peopleId=" + peopleId +
                ", rolId=" + rolId +
                '}';
    }
}
