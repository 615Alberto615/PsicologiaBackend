package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "conclusions")
@NamedQueries({
        @NamedQuery(name = "Conclusions.findAll", query = "SELECT c FROM Conclusions c"),
        @NamedQuery(name = "Conclusions.findByConclusionsId", query = "SELECT c FROM Conclusions c WHERE c.conclusionsId = :conclusionsId"),
        @NamedQuery(name = "Conclusions.findByContent", query = "SELECT c FROM Conclusions c WHERE c.content = :content")})
public class Conclusions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conclusions_id")
    private Integer conclusionsId;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "post_treatment_id", referencedColumnName = "post_treatment_id")
    @ManyToOne(optional = false)
    private PostTreatment postTreatmentId;

    public Conclusions() {
    }

    public Conclusions(Integer conclusionsId) {
        this.conclusionsId = conclusionsId;
    }

    public Conclusions(Integer conclusionsId, String content) {
        this.conclusionsId = conclusionsId;
        this.content = content;
    }

    public Integer getConclusionsId() {
        return conclusionsId;
    }

    public void setConclusionsId(Integer conclusionsId) {
        this.conclusionsId = conclusionsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostTreatment getPostTreatmentId() {
        return postTreatmentId;
    }

    public void setPostTreatmentId(PostTreatment postTreatmentId) {
        this.postTreatmentId = postTreatmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conclusionsId != null ? conclusionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conclusions)) {
            return false;
        }
        Conclusions other = (Conclusions) object;
        if ((this.conclusionsId == null && other.conclusionsId != null) || (this.conclusionsId != null && !this.conclusionsId.equals(other.conclusionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Conclusions[ conclusionsId=" + conclusionsId + " ]";
    }

}