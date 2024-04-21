package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "availability_office")
@NamedQueries({
        @NamedQuery(name = "AvailabilityOffice.findAll", query = "SELECT a FROM AvailabilityOffice a"),
        @NamedQuery(name = "AvailabilityOffice.findByAvailabilityOfficeId", query = "SELECT a FROM AvailabilityOffice a WHERE a.availabilityOfficeId = :availabilityOfficeId")})
public class AvailabilityOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "availability_office_id")
    private Integer availabilityOfficeId;
    @JoinColumn(name = "availability_id", referencedColumnName = "availability_id")
    @ManyToOne(optional = false)
    private Availability availabilityId;
    @JoinColumn(name = "office_id", referencedColumnName = "office_id")
    @ManyToOne(optional = false)
    private Office officeId;

    public AvailabilityOffice() {
    }

    public AvailabilityOffice(Integer availabilityOfficeId) {
        this.availabilityOfficeId = availabilityOfficeId;
    }

    public Integer getAvailabilityOfficeId() {
        return availabilityOfficeId;
    }

    public void setAvailabilityOfficeId(Integer availabilityOfficeId) {
        this.availabilityOfficeId = availabilityOfficeId;
    }

    public Availability getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Availability availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Office getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Office officeId) {
        this.officeId = officeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availabilityOfficeId != null ? availabilityOfficeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvailabilityOffice)) {
            return false;
        }
        AvailabilityOffice other = (AvailabilityOffice) object;
        if ((this.availabilityOfficeId == null && other.availabilityOfficeId != null) || (this.availabilityOfficeId != null && !this.availabilityOfficeId.equals(other.availabilityOfficeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AvailabilityOffice[ availabilityOfficeId=" + availabilityOfficeId + " ]";
    }

}