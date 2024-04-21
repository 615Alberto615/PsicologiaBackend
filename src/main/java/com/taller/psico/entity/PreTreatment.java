package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "pre_treatment")
@NamedQueries({
        @NamedQuery(name = "PreTreatment.findAll", query = "SELECT p FROM PreTreatment p"),
        @NamedQuery(name = "PreTreatment.findByPreTreatmentId", query = "SELECT p FROM PreTreatment p WHERE p.preTreatmentId = :preTreatmentId")})
public class PreTreatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pre_treatment_id")
    private Integer preTreatmentId;
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    @ManyToOne(optional = false)
    private Treatment treatmentId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Useri userId;

    public PreTreatment() {
    }

    public PreTreatment(Integer preTreatmentId) {
        this.preTreatmentId = preTreatmentId;
    }

    public Integer getPreTreatmentId() {
        return preTreatmentId;
    }

    public void setPreTreatmentId(Integer preTreatmentId) {
        this.preTreatmentId = preTreatmentId;
    }

    public Treatment getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Treatment treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Useri getUserId() {
        return userId;
    }

    public void setUserId(Useri userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preTreatmentId != null ? preTreatmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreTreatment)) {
            return false;
        }
        PreTreatment other = (PreTreatment) object;
        if ((this.preTreatmentId == null && other.preTreatmentId != null) || (this.preTreatmentId != null && !this.preTreatmentId.equals(other.preTreatmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PreTreatment[ preTreatmentId=" + preTreatmentId + " ]";
    }

}
