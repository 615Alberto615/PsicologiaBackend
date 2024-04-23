package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "prescription")
@NamedQueries({
        @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p"),
        @NamedQuery(name = "Prescription.findByPrescriptionId", query = "SELECT p FROM Prescription p WHERE p.prescriptionId = :prescriptionId"),
        @NamedQuery(name = "Prescription.findByContentPrescription", query = "SELECT p FROM Prescription p WHERE p.contentPrescription = :contentPrescription")})
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prescription_id")
    private Integer prescriptionId;
    @Basic(optional = false)
    @Column(name = "content_prescription")
    private String contentPrescription;
    @JoinColumn(name = "clinical_advances_id", referencedColumnName = "clinical_advances_id")
    @ManyToOne(optional = false)
    private ClinicalAdvances clinicalAdvancesId;

    public Prescription() {
    }

    public Prescription(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Prescription(Integer prescriptionId, String contentPrescription) {
        this.prescriptionId = prescriptionId;
        this.contentPrescription = contentPrescription;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getContentPrescription() {
        return contentPrescription;
    }

    public void setContentPrescription(String contentPrescription) {
        this.contentPrescription = contentPrescription;
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
        hash += (prescriptionId != null ? prescriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.prescriptionId == null && other.prescriptionId != null) || (this.prescriptionId != null && !this.prescriptionId.equals(other.prescriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prescription[ prescriptionId=" + prescriptionId + " ]";
    }

}
