package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "useri")
@NamedQueries({
        @NamedQuery(name = "Useri.findAll", query = "SELECT u FROM Useri u"),
        @NamedQuery(name = "Useri.findByUserId", query = "SELECT u FROM Useri u WHERE u.userId = :userId"),
        @NamedQuery(name = "Useri.findByUserName", query = "SELECT u FROM Useri u WHERE u.userName = :userName"),
        @NamedQuery(name = "Useri.findByPassword", query = "SELECT u FROM Useri u WHERE u.password = :password"),
        @NamedQuery(name = "Useri.findByStatus", query = "SELECT u FROM Useri u WHERE u.status = :status")})
public class Useri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "reset_password_token", nullable = true, length = 250)
    private String resetPasswordToken;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userPatientId")
    private Collection<Treatment> treatmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userPsychiatristId")
    private Collection<Treatment> treatmentCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Availability> availabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Quotes> quotesCollection;
    @JoinColumn(name = "people_id", referencedColumnName = "people_id")
    @ManyToOne(optional = false)
    private People peopleId;
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    @ManyToOne(optional = false)
    private Rol rolId;

    @Column(name = "bloqueado ", nullable = true)
    private Integer bloqueado;
    @Column(name = "tiempo_bloqueo", nullable = true)
    private LocalDateTime tiempoBloqueo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<PreTreatment> preTreatmentCollection;

    public Useri() {
    }

    public Useri(Integer userId) {
        this.userId = userId;
    }

    public Useri(Integer userId, String userName, String password, boolean status) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public Integer getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Integer bloqueado) {
        this.bloqueado = bloqueado;
    }

    public LocalDateTime getTiempoBloqueo() {
        return tiempoBloqueo;
    }

    public void setTiempoBloqueo(LocalDateTime tiempoBloqueo) {
        this.tiempoBloqueo = tiempoBloqueo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<Treatment> getTreatmentCollection() {
        return treatmentCollection;
    }

    public void setTreatmentCollection(Collection<Treatment> treatmentCollection) {
        this.treatmentCollection = treatmentCollection;
    }

    public Collection<Treatment> getTreatmentCollection1() {
        return treatmentCollection1;
    }

    public void setTreatmentCollection1(Collection<Treatment> treatmentCollection1) {
        this.treatmentCollection1 = treatmentCollection1;
    }

    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
    }

    public Collection<Quotes> getQuotesCollection() {
        return quotesCollection;
    }

    public void setQuotesCollection(Collection<Quotes> quotesCollection) {
        this.quotesCollection = quotesCollection;
    }

    public People getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(People peopleId) {
        this.peopleId = peopleId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useri)) {
            return false;
        }
        Useri other = (Useri) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Useri[ userId=" + userId + " ]";
    }

}

