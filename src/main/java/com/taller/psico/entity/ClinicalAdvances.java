package com.taller.psico.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "clinical_advances")
@NamedQueries({
        @NamedQuery(name = "ClinicalAdvances.findAll", query = "SELECT c FROM ClinicalAdvances c"),
        @NamedQuery(name = "ClinicalAdvances.findByClinicalAdvancesId", query = "SELECT c FROM ClinicalAdvances c WHERE c.clinicalAdvancesId = :clinicalAdvancesId"),
        @NamedQuery(name = "ClinicalAdvances.findByDescriptionAdvance", query = "SELECT c FROM ClinicalAdvances c WHERE c.descriptionAdvance = :descriptionAdvance"),
        @NamedQuery(name = "ClinicalAdvances.findByDateAdvance", query = "SELECT c FROM ClinicalAdvances c WHERE c.dateAdvance = :dateAdvance"),
        @NamedQuery(name = "ClinicalAdvances.findByInstructionGrade", query = "SELECT c FROM ClinicalAdvances c WHERE c.instructionGrade = :instructionGrade"),
        @NamedQuery(name = "ClinicalAdvances.findByCompanions", query = "SELECT c FROM ClinicalAdvances c WHERE c.companions = :companions"),
        @NamedQuery(name = "ClinicalAdvances.findByDoctorDerivation", query = "SELECT c FROM ClinicalAdvances c WHERE c.doctorDerivation = :doctorDerivation"),
        @NamedQuery(name = "ClinicalAdvances.findByPlaceDerivation", query = "SELECT c FROM ClinicalAdvances c WHERE c.placeDerivation = :placeDerivation"),
        @NamedQuery(name = "ClinicalAdvances.findByReceptionDate", query = "SELECT c FROM ClinicalAdvances c WHERE c.receptionDate = :receptionDate"),
        @NamedQuery(name = "ClinicalAdvances.findByStatus", query = "SELECT c FROM ClinicalAdvances c WHERE c.status = :status"),
        @NamedQuery(name = "ClinicalAdvances.findByTypeIntervation", query = "SELECT c FROM ClinicalAdvances c WHERE c.typeIntervation = :typeIntervation")})
public class ClinicalAdvances implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clinical_advances_id")
    private Integer clinicalAdvancesId;
    @Basic(optional = false)
    @Column(name = "description_advance")
    private String descriptionAdvance;
    @Basic(optional = false)
    @Column(name = "date_advance")
    @Temporal(TemporalType.DATE)
    private Date dateAdvance;
    @Basic(optional = false)
    @Column(name = "instruction_grade")
    private String instructionGrade;
    @Basic(optional = false)
    @Column(name = "companions")
    private String companions;
    @Basic(optional = false)
    @Column(name = "doctor_derivation")
    private String doctorDerivation;
    @Basic(optional = false)
    @Column(name = "place_derivation")
    private String placeDerivation;
    @Basic(optional = false)
    @Column(name = "reception_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receptionDate;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "type_intervation")
    private String typeIntervation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinicalAdvancesId")
    private Collection<Observation> observationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinicalAdvancesId")
    private Collection<Prescription> prescriptionCollection;
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    @ManyToOne(optional = false)
    private Treatment treatmentId;

    public ClinicalAdvances() {
    }

    public ClinicalAdvances(Integer clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    public ClinicalAdvances(Integer clinicalAdvancesId, String descriptionAdvance, Date dateAdvance, String instructionGrade, String companions, String doctorDerivation, String placeDerivation, Date receptionDate, boolean status, String typeIntervation) {
        this.clinicalAdvancesId = clinicalAdvancesId;
        this.descriptionAdvance = descriptionAdvance;
        this.dateAdvance = dateAdvance;
        this.instructionGrade = instructionGrade;
        this.companions = companions;
        this.doctorDerivation = doctorDerivation;
        this.placeDerivation = placeDerivation;
        this.receptionDate = receptionDate;
        this.status = status;
        this.typeIntervation = typeIntervation;
    }

    public Integer getClinicalAdvancesId() {
        return clinicalAdvancesId;
    }

    public void setClinicalAdvancesId(Integer clinicalAdvancesId) {
        this.clinicalAdvancesId = clinicalAdvancesId;
    }

    public String getDescriptionAdvance() {
        return descriptionAdvance;
    }

    public void setDescriptionAdvance(String descriptionAdvance) {
        this.descriptionAdvance = descriptionAdvance;
    }

    public Date getDateAdvance() {
        return dateAdvance;
    }

    public void setDateAdvance(Date dateAdvance) {
        this.dateAdvance = dateAdvance;
    }

    public String getInstructionGrade() {
        return instructionGrade;
    }

    public void setInstructionGrade(String instructionGrade) {
        this.instructionGrade = instructionGrade;
    }

    public String getCompanions() {
        return companions;
    }

    public void setCompanions(String companions) {
        this.companions = companions;
    }

    public String getDoctorDerivation() {
        return doctorDerivation;
    }

    public void setDoctorDerivation(String doctorDerivation) {
        this.doctorDerivation = doctorDerivation;
    }

    public String getPlaceDerivation() {
        return placeDerivation;
    }

    public void setPlaceDerivation(String placeDerivation) {
        this.placeDerivation = placeDerivation;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTypeIntervation() {
        return typeIntervation;
    }

    public void setTypeIntervation(String typeIntervation) {
        this.typeIntervation = typeIntervation;
    }

    public Collection<Observation> getObservationCollection() {
        return observationCollection;
    }

    public void setObservationCollection(Collection<Observation> observationCollection) {
        this.observationCollection = observationCollection;
    }

    public Collection<Prescription> getPrescriptionCollection() {
        return prescriptionCollection;
    }

    public void setPrescriptionCollection(Collection<Prescription> prescriptionCollection) {
        this.prescriptionCollection = prescriptionCollection;
    }

    public Treatment getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Treatment treatmentId) {
        this.treatmentId = treatmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalAdvancesId != null ? clinicalAdvancesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalAdvances)) {
            return false;
        }
        ClinicalAdvances other = (ClinicalAdvances) object;
        if ((this.clinicalAdvancesId == null && other.clinicalAdvancesId != null) || (this.clinicalAdvancesId != null && !this.clinicalAdvancesId.equals(other.clinicalAdvancesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClinicalAdvances[ clinicalAdvancesId=" + clinicalAdvancesId + " ]";
    }

}

