package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "office")
@NamedQueries({
        @NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o"),
        @NamedQuery(name = "Office.findByOfficeId", query = "SELECT o FROM Office o WHERE o.officeId = :officeId"),
        @NamedQuery(name = "Office.findByAddress", query = "SELECT o FROM Office o WHERE o.address = :address"),
        @NamedQuery(name = "Office.findByStatus", query = "SELECT o FROM Office o WHERE o.status = :status")})
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "office_id")
    private Integer officeId;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "officeId")
    private Collection<AvailabilityOffice> availabilityOfficeCollection;

    public Office() {
    }

    public Office(Integer officeId) {
        this.officeId = officeId;
    }

    public Office(Integer officeId, String address, boolean status) {
        this.officeId = officeId;
        this.address = address;
        this.status = status;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<AvailabilityOffice> getAvailabilityOfficeCollection() {
        return availabilityOfficeCollection;
    }

    public void setAvailabilityOfficeCollection(Collection<AvailabilityOffice> availabilityOfficeCollection) {
        this.availabilityOfficeCollection = availabilityOfficeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officeId != null ? officeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.officeId == null && other.officeId != null) || (this.officeId != null && !this.officeId.equals(other.officeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Office[ officeId=" + officeId + " ]";
    }

}
