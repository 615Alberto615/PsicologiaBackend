package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "treatment")
@NamedQueries({
        @NamedQuery(name = "Treatment.findAll", query = "SELECT t FROM Treatment t"),
        @NamedQuery(name = "Treatment.findByTreatmentId", query = "SELECT t FROM Treatment t WHERE t.treatmentId = :treatmentId"),
        @NamedQuery(name = "Treatment.findByName", query = "SELECT t FROM Treatment t WHERE t.name = :name"),
        @NamedQuery(name = "Treatment.findByStartDate", query = "SELECT t FROM Treatment t WHERE t.startDate = :startDate"),
        @NamedQuery(name = "Treatment.findByStatus", query = "SELECT t FROM Treatment t WHERE t.status = :status")})
public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "treatment_id")
    private Integer treatmentId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "user_patient_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Useri userPatientId;
    @JoinColumn(name = "user_psychiatrist_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Useri userPsychiatristId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatmentId")
    private Collection<PostTreatment> postTreatmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatmentId")
    private Collection<ClinicalAdvances> clinicalAdvancesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatmentId")
    private Collection<PreTreatment> preTreatmentCollection;

    public Treatment() {
    }

    public Treatment(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Treatment(Integer treatmentId, String name, Date startDate, boolean status) {
        this.treatmentId = treatmentId;
        this.name = name;
        this.startDate = startDate;
        this.status = status;
    }

    public Integer getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Integer treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Useri getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(Useri userPatientId) {
        this.userPatientId = userPatientId;
    }

    public Useri getUserPsychiatristId() {
        return userPsychiatristId;
    }

    public void setUserPsychiatristId(Useri userPsychiatristId) {
        this.userPsychiatristId = userPsychiatristId;
    }

    public Collection<PostTreatment> getPostTreatmentCollection() {
        return postTreatmentCollection;
    }

    public void setPostTreatmentCollection(Collection<PostTreatment> postTreatmentCollection) {
        this.postTreatmentCollection = postTreatmentCollection;
    }

    public Collection<ClinicalAdvances> getClinicalAdvancesCollection() {
        return clinicalAdvancesCollection;
    }

    public void setClinicalAdvancesCollection(Collection<ClinicalAdvances> clinicalAdvancesCollection) {
        this.clinicalAdvancesCollection = clinicalAdvancesCollection;
    }

    public Collection<PreTreatment> getPreTreatmentCollection() {
        return preTreatmentCollection;
    }

    public void setPreTreatmentCollection(Collection<PreTreatment> preTreatmentCollection) {
        this.preTreatmentCollection = preTreatmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (treatmentId != null ? treatmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatment)) {
            return false;
        }
        Treatment other = (Treatment) object;
        if ((this.treatmentId == null && other.treatmentId != null) || (this.treatmentId != null && !this.treatmentId.equals(other.treatmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Treatment[ treatmentId=" + treatmentId + " ]";
    }

}

