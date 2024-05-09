package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "availability")
@NamedQueries({
        @NamedQuery(name = "Availability.findAll", query = "SELECT a FROM Availability a"),
        @NamedQuery(name = "Availability.findByAvailabilityId", query = "SELECT a FROM Availability a WHERE a.availabilityId = :availabilityId"),
        @NamedQuery(name = "Availability.findByWeekday", query = "SELECT a FROM Availability a WHERE a.weekday = :weekday"),
        @NamedQuery(name = "Availability.findByStartTime", query = "SELECT a FROM Availability a WHERE a.startTime = :startTime"),
        @NamedQuery(name = "Availability.findByEndTime", query = "SELECT a FROM Availability a WHERE a.endTime = :endTime"),
        @NamedQuery(name = "Availability.findByCodeAvailability", query = "SELECT a FROM Availability a WHERE a.codeAvailability = :codeAvailability"),
        @NamedQuery(name = "Availability.findByStatus", query = "SELECT a FROM Availability a WHERE a.status = :status")})
public class Availability implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "availability_id")
    private Integer availabilityId;
    @Basic(optional = false)
    @Column(name = "weekday")
    private String weekday;
    @Basic(optional = false)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "code_availability")
    private int codeAvailability;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Useri userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "availabilityId")
    private Collection<Quotes> quotesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "availabilityId")
    private Collection<AvailabilityOffice> availabilityOfficeCollection;

    public Availability() {
    }

    public Availability(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Availability(Integer availabilityId, String weekday, Date startTime, Date endTime, int codeAvailability, boolean status, Useri userId, Collection<Quotes> quotesCollection, Collection<AvailabilityOffice> availabilityOfficeCollection) {
        this.availabilityId = availabilityId;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.codeAvailability = codeAvailability;
        this.status = status;
        this.userId = userId;
        this.quotesCollection = quotesCollection;
        this.availabilityOfficeCollection = availabilityOfficeCollection;
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

    public Useri getUserId() {
        return userId;
    }

    public void setUserId(Useri userId) {
        this.userId = userId;
    }

    public Collection<Quotes> getQuotesCollection() {
        return quotesCollection;
    }

    public void setQuotesCollection(Collection<Quotes> quotesCollection) {
        this.quotesCollection = quotesCollection;
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
        hash += (availabilityId != null ? availabilityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availabilityId == null && other.availabilityId != null) || (this.availabilityId != null && !this.availabilityId.equals(other.availabilityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Availability[ availabilityId=" + availabilityId + " ]";
    }

}

