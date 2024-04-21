package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name = "post_treatment")
@NamedQueries({
        @NamedQuery(name = "PostTreatment.findAll", query = "SELECT p FROM PostTreatment p"),
        @NamedQuery(name = "PostTreatment.findByPostTreatmentId", query = "SELECT p FROM PostTreatment p WHERE p.postTreatmentId = :postTreatmentId"),
        @NamedQuery(name = "PostTreatment.findByEndTreatment", query = "SELECT p FROM PostTreatment p WHERE p.endTreatment = :endTreatment"),
        @NamedQuery(name = "PostTreatment.findByReopeningCase", query = "SELECT p FROM PostTreatment p WHERE p.reopeningCase = :reopeningCase"),
        @NamedQuery(name = "PostTreatment.findByWhyReopeningCase", query = "SELECT p FROM PostTreatment p WHERE p.whyReopeningCase = :whyReopeningCase")})
public class PostTreatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "post_treatment_id")
    private Integer postTreatmentId;
    @Basic(optional = false)
    @Column(name = "end_treatment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTreatment;
    @Basic(optional = false)
    @Column(name = "reopening_case")
    private boolean reopeningCase;
    @Column(name = "why_reopening_case")
    private String whyReopeningCase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postTreatmentId")
    private Collection<Conclusions> conclusionsCollection;
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    @ManyToOne(optional = false)
    private Treatment treatmentId;

    public PostTreatment() {
    }

    public PostTreatment(Integer postTreatmentId) {
        this.postTreatmentId = postTreatmentId;
    }

    public PostTreatment(Integer postTreatmentId, Date endTreatment, boolean reopeningCase) {
        this.postTreatmentId = postTreatmentId;
        this.endTreatment = endTreatment;
        this.reopeningCase = reopeningCase;
    }

    public Integer getPostTreatmentId() {
        return postTreatmentId;
    }

    public void setPostTreatmentId(Integer postTreatmentId) {
        this.postTreatmentId = postTreatmentId;
    }

    public Date getEndTreatment() {
        return endTreatment;
    }

    public void setEndTreatment(Date endTreatment) {
        this.endTreatment = endTreatment;
    }

    public boolean getReopeningCase() {
        return reopeningCase;
    }

    public void setReopeningCase(boolean reopeningCase) {
        this.reopeningCase = reopeningCase;
    }

    public String getWhyReopeningCase() {
        return whyReopeningCase;
    }

    public void setWhyReopeningCase(String whyReopeningCase) {
        this.whyReopeningCase = whyReopeningCase;
    }

    public Collection<Conclusions> getConclusionsCollection() {
        return conclusionsCollection;
    }

    public void setConclusionsCollection(Collection<Conclusions> conclusionsCollection) {
        this.conclusionsCollection = conclusionsCollection;
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
        hash += (postTreatmentId != null ? postTreatmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostTreatment)) {
            return false;
        }
        PostTreatment other = (PostTreatment) object;
        if ((this.postTreatmentId == null && other.postTreatmentId != null) || (this.postTreatmentId != null && !this.postTreatmentId.equals(other.postTreatmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PostTreatment[ postTreatmentId=" + postTreatmentId + " ]";
    }

}