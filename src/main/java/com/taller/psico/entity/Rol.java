package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "rol")
@NamedQueries({
        @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
        @NamedQuery(name = "Rol.findByRolId", query = "SELECT r FROM Rol r WHERE r.rolId = :rolId"),
        @NamedQuery(name = "Rol.findByRolType", query = "SELECT r FROM Rol r WHERE r.rolType = :rolType"),
        @NamedQuery(name = "Rol.findByStatus", query = "SELECT r FROM Rol r WHERE r.status = :status")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_id")
    private Integer rolId;
    @Basic(optional = false)
    @Column(name = "rol_type")
    private String rolType;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolId")
    private Collection<Useri> useriCollection;

    public Rol() {
    }

    public Rol(Integer rolId) {
        this.rolId = rolId;
    }

    public Rol(Integer rolId, String rolType, boolean status) {
        this.rolId = rolId;
        this.rolType = rolType;
        this.status = status;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<Useri> getUseriCollection() {
        return useriCollection;
    }

    public void setUseriCollection(Collection<Useri> useriCollection) {
        this.useriCollection = useriCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolId != null ? rolId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolId == null && other.rolId != null) || (this.rolId != null && !this.rolId.equals(other.rolId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rol[ rolId=" + rolId + " ]";
    }

}