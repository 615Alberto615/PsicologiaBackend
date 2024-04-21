package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "type_notification")
@NamedQueries({
        @NamedQuery(name = "TypeNotification.findAll", query = "SELECT t FROM TypeNotification t"),
        @NamedQuery(name = "TypeNotification.findByTypeNotificationId", query = "SELECT t FROM TypeNotification t WHERE t.typeNotificationId = :typeNotificationId"),
        @NamedQuery(name = "TypeNotification.findByType", query = "SELECT t FROM TypeNotification t WHERE t.type = :type")})
public class TypeNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "type_notification_id")
    private Integer typeNotificationId;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeNotificationId")
    private Collection<Notification> notificationCollection;

    public TypeNotification() {
    }

    public TypeNotification(Integer typeNotificationId) {
        this.typeNotificationId = typeNotificationId;
    }

    public TypeNotification(Integer typeNotificationId, int type) {
        this.typeNotificationId = typeNotificationId;
        this.type = type;
    }

    public Integer getTypeNotificationId() {
        return typeNotificationId;
    }

    public void setTypeNotificationId(Integer typeNotificationId) {
        this.typeNotificationId = typeNotificationId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeNotificationId != null ? typeNotificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeNotification)) {
            return false;
        }
        TypeNotification other = (TypeNotification) object;
        if ((this.typeNotificationId == null && other.typeNotificationId != null) || (this.typeNotificationId != null && !this.typeNotificationId.equals(other.typeNotificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TypeNotification[ typeNotificationId=" + typeNotificationId + " ]";
    }

}