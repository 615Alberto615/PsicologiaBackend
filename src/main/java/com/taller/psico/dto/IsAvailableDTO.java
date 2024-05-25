package com.taller.psico.dto;

import java.util.Date;

public class IsAvailableDTO {

    private Integer availabilityId;
    private Date startTime;

    public IsAvailableDTO() {
    }

    public IsAvailableDTO(Integer availabilityId, Date startTime) {
        this.availabilityId = availabilityId;
        this.startTime = startTime;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "IsAvailableDTO{" +
                "availabilityId=" + availabilityId +
                ", startTime=" + startTime +
                '}';
    }
}
