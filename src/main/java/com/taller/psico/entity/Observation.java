package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "observation")
@NamedQueries({
        @NamedQuery(name = "Observation.findAll", query = "SELECT o FROM Observation o"),
        @NamedQuery(name = "Observation.findByObservationId", query = "SELECT o FROM Observation o WHERE o.observationId = :observationId"),
        @NamedQuery(name = "Observation.findByObservationContent", query = "SELECT o FROM Observation o WHERE o.observationContent = :observationContent")})
public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "observation_id")
    private Integer observationId;
    @Basic(optional = false)
    @Column(name = "observation_content")
    private String observationContent;
    @JoinColumn(name = "clinical_advances_id", referencedColumnName = "clinical_advances_id")
    @ManyToOne(optional = false)
    private ClinicalAdvances clinicalAdvancesId;

    public Observation() {
    }

    public Observation(Integer observationId) {
        this.observationId = observationId;
    }

    public Observation(Integer observationId, String observationContent) {
        this.observationId = observationId;
        this.observationContent = observationContent;
    }

    public Integer getObservationId() {
        return observationId;
    }

    public void setObservationId(Integer observationId) {
        this.observationId = observationId;
    }

    public String getObservationContent() {
        return observationContent;
    }

    public void setObservationContent(String observationContent) {
        this.observationContent = observationContent;
    }

    public ClinicalAdvances getClinicalAdvancesId() {
        return clinicalAdvancesId;
    }

    public void setClinicalAdvancesId(ClinicalAdvances clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observationId != null ? observationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.observationId == null && other.observationId != null) || (this.observationId != null && !this.observationId.equals(other.observationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Observation[ observationId=" + observationId + " ]";
    }

}

