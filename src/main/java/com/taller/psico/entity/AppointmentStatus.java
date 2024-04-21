package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "appointment_status")
@NamedQueries({
        @NamedQuery(name = "AppointmentStatus.findAll", query = "SELECT a FROM AppointmentStatus a"),
        @NamedQuery(name = "AppointmentStatus.findByAppointmentStatusId", query = "SELECT a FROM AppointmentStatus a WHERE a.appointmentStatusId = :appointmentStatusId"),
        @NamedQuery(name = "AppointmentStatus.findByNameStatus", query = "SELECT a FROM AppointmentStatus a WHERE a.nameStatus = :nameStatus"),
        @NamedQuery(name = "AppointmentStatus.findByStatus", query = "SELECT a FROM AppointmentStatus a WHERE a.status = :status")})
public class AppointmentStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "appointment_status_id")
    private Integer appointmentStatusId;
    @Basic(optional = false)
    @Column(name = "name_status")
    private String nameStatus;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointmentStatusId")
    private Collection<Quotes> quotesCollection;

    public AppointmentStatus() {
    }

    public AppointmentStatus(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public AppointmentStatus(Integer appointmentStatusId, String nameStatus, boolean status) {
        this.appointmentStatusId = appointmentStatusId;
        this.nameStatus = nameStatus;
        this.status = status;
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<Quotes> getQuotesCollection() {
        return quotesCollection;
    }

    public void setQuotesCollection(Collection<Quotes> quotesCollection) {
        this.quotesCollection = quotesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentStatusId != null ? appointmentStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentStatus)) {
            return false;
        }
        AppointmentStatus other = (AppointmentStatus) object;
        if ((this.appointmentStatusId == null && other.appointmentStatusId != null) || (this.appointmentStatusId != null && !this.appointmentStatusId.equals(other.appointmentStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AppointmentStatus[ appointmentStatusId=" + appointmentStatusId + " ]";
    }

}