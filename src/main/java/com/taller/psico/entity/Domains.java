package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "domains")
@NamedQueries({
        @NamedQuery(name = "Domains.findAll", query = "SELECT d FROM Domains d"),
        @NamedQuery(name = "Domains.findByDomainsId", query = "SELECT d FROM Domains d WHERE d.domainsId = :domainsId"),
        @NamedQuery(name = "Domains.findByType", query = "SELECT d FROM Domains d WHERE d.type = :type"),
        @NamedQuery(name = "Domains.findByName", query = "SELECT d FROM Domains d WHERE d.name = :name"),
        @NamedQuery(name = "Domains.findByDescription", query = "SELECT d FROM Domains d WHERE d.description = :description")})
public class Domains implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "domains_id")
    private Integer domainsId;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genderId")
    private Collection<People> peopleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "occupationId")
    private Collection<People> peopleCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private Collection<People> peopleCollection2;

    public Domains() {
    }

    public Domains(Integer domainsId) {
        this.domainsId = domainsId;
    }

    public Domains(Integer domainsId, String type, String name, String description) {
        this.domainsId = domainsId;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    public Integer getDomainsId() {
        return domainsId;
    }

    public void setDomainsId(Integer domainsId) {
        this.domainsId = domainsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<People> getPeopleCollection() {
        return peopleCollection;
    }

    public void setPeopleCollection(Collection<People> peopleCollection) {
        this.peopleCollection = peopleCollection;
    }

    public Collection<People> getPeopleCollection1() {
        return peopleCollection1;
    }

    public void setPeopleCollection1(Collection<People> peopleCollection1) {
        this.peopleCollection1 = peopleCollection1;
    }

    public Collection<People> getPeopleCollection2() {
        return peopleCollection2;
    }

    public void setPeopleCollection2(Collection<People> peopleCollection2) {
        this.peopleCollection2 = peopleCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domainsId != null ? domainsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domains)) {
            return false;
        }
        Domains other = (Domains) object;
        if ((this.domainsId == null && other.domainsId != null) || (this.domainsId != null && !this.domainsId.equals(other.domainsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Domains[ domainsId=" + domainsId + " ]";
    }

}

