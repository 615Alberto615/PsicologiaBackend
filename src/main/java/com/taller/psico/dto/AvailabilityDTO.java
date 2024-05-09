package com.taller.psico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AvailabilityDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer availabilityId;

    private String  weekday;

    @JsonFormat(pattern="HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern="HH:mm:ss")
    private Date endTime;
    private int codeAvailability;
    private boolean status;
    private UseriDTO user; // Sólo el ID del usuario asociado

    // Constructor vacío
    public AvailabilityDTO() {
    }

    // Constructor con todos los campos


    public AvailabilityDTO(Integer availabilityId, String weekday, Date startTime, Date endTime, int codeAvailability, boolean status, UseriDTO user) {
        this.availabilityId = availabilityId;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.codeAvailability = codeAvailability;
        this.status = status;
        this.user = user;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
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

    public UseriDTO getUser() {
        return user;
    }

    public void setUser(UseriDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AvailabilityDTO{" +
                "availabilityId=" + availabilityId +
                ", weekday='" + weekday + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", codeAvailability=" + codeAvailability +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
