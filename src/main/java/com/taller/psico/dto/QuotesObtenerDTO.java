package com.taller.psico.dto;

import java.util.Date;

public class QuotesObtenerDTO {

    private Integer quotesId;
    private String reason;
    private String typeQuotes;
    private boolean status;
    private Date appointmentRequest;
    private Integer appointmentStatusId;
    private AvailabilityDTO availability; // Cambiado de availabilityId a AvailabilityDTO
    private UseriObtenerDTO user; // Cambiado de userId a UseriDTO
    private Date startTime;
    private Date endTime;

    public QuotesObtenerDTO() {
    }

    public QuotesObtenerDTO(Integer quotesId, String reason, String typeQuotes, boolean status, Date appointmentRequest, Integer appointmentStatusId, AvailabilityDTO availability, UseriObtenerDTO user, Date startTime, Date endTime) {
        this.quotesId = quotesId;
        this.reason = reason;
        this.typeQuotes = typeQuotes;
        this.status = status;
        this.appointmentRequest = appointmentRequest;
        this.appointmentStatusId = appointmentStatusId;
        this.availability = availability;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public AvailabilityDTO getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityDTO availability) {
        this.availability = availability;
    }

    public UseriObtenerDTO getUser() {
        return user;
    }

    public void setUser(UseriObtenerDTO user) {
        this.user = user;
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
}
