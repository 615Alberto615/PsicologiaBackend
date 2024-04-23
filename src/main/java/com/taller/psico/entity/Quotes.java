package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "quotes")
@NamedQueries({
        @NamedQuery(name = "Quotes.findAll", query = "SELECT q FROM Quotes q"),
        @NamedQuery(name = "Quotes.findByQuotesId", query = "SELECT q FROM Quotes q WHERE q.quotesId = :quotesId"),
        @NamedQuery(name = "Quotes.findByReason", query = "SELECT q FROM Quotes q WHERE q.reason = :reason"),
        @NamedQuery(name = "Quotes.findByTypeQuotes", query = "SELECT q FROM Quotes q WHERE q.typeQuotes = :typeQuotes"),
        @NamedQuery(name = "Quotes.findByStatus", query = "SELECT q FROM Quotes q WHERE q.status = :status"),
        @NamedQuery(name = "Quotes.findByAppointmentRequest", query = "SELECT q FROM Quotes q WHERE q.appointmentRequest = :appointmentRequest")})
public class Quotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "quotes_id")
    private Integer quotesId;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "type_quotes")
    private String typeQuotes;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "appointment_request")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentRequest;
    @JoinColumn(name = "appointment_status_id", referencedColumnName = "appointment_status_id")
    @ManyToOne(optional = false)
    private AppointmentStatus appointmentStatusId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "availability_id", referencedColumnName = "availability_id")
    private Availability availabilityId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Useri userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quotesId")
    private Collection<Notification> notificationCollection;

    public Quotes() {
    }

    public Quotes(Integer quotesId) {
        this.quotesId = quotesId;
    }

    public Quotes(Integer quotesId, String reason, String typeQuotes, boolean status, Date appointmentRequest) {
        this.quotesId = quotesId;
        this.reason = reason;
        this.typeQuotes = typeQuotes;
        this.status = status;
        this.appointmentRequest = appointmentRequest;
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

    public boolean getStatus() {
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

    public AppointmentStatus getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(AppointmentStatus appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public Availability getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Availability availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Useri getUserId() {
        return userId;
    }

    public void setUserId(Useri userId) {
        this.userId = userId;
    }

    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quotesId != null ? quotesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quotes)) {
            return false;
        }
        Quotes other = (Quotes) object;
        if ((this.quotesId == null && other.quotesId != null) || (this.quotesId != null && !this.quotesId.equals(other.quotesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Quotes[ quotesId=" + quotesId + " ]";
    }

}

