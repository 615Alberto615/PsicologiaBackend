package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notification")
@NamedQueries({
        @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
        @NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notificationId = :notificationId"),
        @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content"),
        @NamedQuery(name = "Notification.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date"),
        @NamedQuery(name = "Notification.findByTime", query = "SELECT n FROM Notification n WHERE n.time = :time"),
        @NamedQuery(name = "Notification.findByStatus", query = "SELECT n FROM Notification n WHERE n.status = :status")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "notification_id")
    private Integer notificationId;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "quotes_id", referencedColumnName = "quotes_id")
    @ManyToOne(optional = false)
    private Quotes quotesId;
    @JoinColumn(name = "type_notification_id", referencedColumnName = "type_notification_id")
    @ManyToOne(optional = false)
    private TypeNotification typeNotificationId;

    public Notification() {
    }

    public Notification(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Notification(Integer notificationId, String content, Date date, Date time, boolean status) {
        this.notificationId = notificationId;
        this.content = content;
        this.date = date;
        this.time = time;
        this.status = status;
    }

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

    public Quotes getQuotesId() {
        return quotesId;
    }

    public void setQuotesId(Quotes quotesId) {
        this.quotesId = quotesId;
    }

    public TypeNotification getTypeNotificationId() {
        return typeNotificationId;
    }

    public void setTypeNotificationId(TypeNotification typeNotificationId) {
        this.typeNotificationId = typeNotificationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Notification[ notificationId=" + notificationId + " ]";
    }

}