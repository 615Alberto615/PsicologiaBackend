package com.taller.psico.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer notificationId;
    private String content;
    private Date date;
    private Date time;
    private boolean status;
    private Integer quotesId;  // Solo el ID de la cita
    private Integer typeNotificationId;  // Solo el ID del tipo de notificación

    // Constructor vacío
    public NotificationDTO() {
    }

    // Constructor con todos los campos
    public NotificationDTO(Integer notificationId, String content, Date date, Date time, boolean status, Integer quotesId, Integer typeNotificationId) {
        this.notificationId = notificationId;
        this.content = content;
        this.date = date;
        this.time = time;
        this.status = status;
        this.quotesId = quotesId;
        this.typeNotificationId = typeNotificationId;
    }

    // Getters y Setters
    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getQuotesId() {
        return quotesId;
    }

    public void setQuotesId(Integer quotesId) {
        this.quotesId = quotesId;
    }

    public Integer getTypeNotificationId() {
        return typeNotificationId;
    }

    public void setTypeNotificationId(Integer typeNotificationId) {
        this.typeNotificationId = typeNotificationId;
    }

    // Método toString
    @Override
    public String toString() {
        return "NotificationDTO{" +
                "notificationId=" + notificationId +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", quotesId=" + quotesId +
                ", typeNotificationId=" + typeNotificationId +
                '}';
    }
}
