package com.taller.psico.dto;

import java.util.Date;

public class QuotesDTO {

    private static final long serialVersionUID = 1L;
    private Integer quotesId;
    private String reason;
    private String typeQuotes;
    private boolean status;
    private Date appointmentRequest;
    private Integer appointmentStatusId;  // Only the ID of the appointment status
    private Integer availabilityId;       // Only the ID of the availability
    private Integer userId;

    public QuotesDTO() {
    }

    public QuotesDTO(Integer quotesId, String reason, String typeQuotes, boolean status, Date appointmentRequest, Integer appointmentStatusId, Integer availabilityId, Integer userId) {
        this.quotesId = quotesId;
        this.reason = reason;
        this.typeQuotes = typeQuotes;
        this.status = status;
        this.appointmentRequest = appointmentRequest;
        this.appointmentStatusId = appointmentStatusId;
        this.availabilityId = availabilityId;
        this.userId = userId;
    }

    public Integer getQuotesId() {
        return quotesId;
    }

    public void setQuotesId(Integer quotesId) {
        this.quotesId = quotesId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTypeQuotes() {
        return typeQuotes;
    }

    public void setTypeQuotes(String typeQuotes) {
        this.typeQuotes = typeQuotes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getAppointmentRequest() {
        return appointmentRequest;
    }

    public void setAppointmentRequest(Date appointmentRequest) {
        this.appointmentRequest = appointmentRequest;
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QuotesDTO{" +
                "quotesId=" + quotesId +
                ", reason='" + reason + '\'' +
                ", typeQuotes='" + typeQuotes + '\'' +
                ", status=" + status +
                ", appointmentRequest=" + appointmentRequest +
                ", appointmentStatusId=" + appointmentStatusId +
                ", availabilityId=" + availabilityId +
                ", userId=" + userId +
                '}';
    }
}
