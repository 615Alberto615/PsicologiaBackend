package com.taller.psico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AvailabilityDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer availabilityId;
    private Date weekday;
    @JsonFormat(pattern="HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="HH:mm:ss")
    private Date endTime;
    private int codeAvailability;
    private boolean status;
    private Integer userId;  // Sólo el ID del usuario asociado

    // Constructor vacío
    public AvailabilityDTO() {
    }

    // Constructor con todos los campos
    public AvailabilityDTO(Integer availabilityId, Date weekday, Date startTime, Date endTime, int codeAvailability, boolean status, Integer userId) {
        this.availabilityId = availabilityId;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.codeAvailability = codeAvailability;
        this.status = status;
        this.userId = userId;
    }

    // Getters y Setters
    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Date getWeekday() {
        return weekday;
    }

    public void setWeekday(Date weekday) {
        this.weekday = weekday;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCodeAvailability() {
        return codeAvailability;
    }

    public void setCodeAvailability(int codeAvailability) {
        this.codeAvailability = codeAvailability;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Método toString
    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "availabilityId=" + availabilityId +
                ", weekday=" + weekday +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", codeAvailability=" + codeAvailability +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
