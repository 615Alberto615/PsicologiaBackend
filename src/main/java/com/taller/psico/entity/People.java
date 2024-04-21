package com.taller.psico.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(name = "people")
@NamedQueries({
        @NamedQuery(name = "People.findAll", query = "SELECT p FROM People p"),
        @NamedQuery(name = "People.findByPeopleId", query = "SELECT p FROM People p WHERE p.peopleId = :peopleId"),
        @NamedQuery(name = "People.findByPhoto", query = "SELECT p FROM People p WHERE p.photo = :photo"),
        @NamedQuery(name = "People.findByName", query = "SELECT p FROM People p WHERE p.name = :name"),
        @NamedQuery(name = "People.findByFirstLastname", query = "SELECT p FROM People p WHERE p.firstLastname = :firstLastname"),
        @NamedQuery(name = "People.findBySecondLastname", query = "SELECT p FROM People p WHERE p.secondLastname = :secondLastname"),
        @NamedQuery(name = "People.findByEmail", query = "SELECT p FROM People p WHERE p.email = :email"),
        @NamedQuery(name = "People.findByAge", query = "SELECT p FROM People p WHERE p.age = :age"),
        @NamedQuery(name = "People.findByCellphone", query = "SELECT p FROM People p WHERE p.cellphone = :cellphone"),
        @NamedQuery(name = "People.findByAddress", query = "SELECT p FROM People p WHERE p.address = :address"),
        @NamedQuery(name = "People.findByCi", query = "SELECT p FROM People p WHERE p.ci = :ci"),
        @NamedQuery(name = "People.findByStatus", query = "SELECT p FROM People p WHERE p.status = :status")})
public class People implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "people_id")
    private Integer peopleId;
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "first_lastname")
    private String firstLastname;
    @Basic(optional = false)
    @Column(name = "second_lastname")
    private String secondLastname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "age")
    @Temporal(TemporalType.DATE)
    private Date age;
    @Basic(optional = false)
    @Column(name = "cellphone")
    private String cellphone;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "ci")
    private String ci;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @JoinColumn(name = "gender_id", referencedColumnName = "domains_id")
    @ManyToOne(optional = false)
    private Domains genderId;
    @JoinColumn(name = "occupation_id", referencedColumnName = "domains_id")
    @ManyToOne(optional = false)
    private Domains occupationId;
    @JoinColumn(name = "semester_id", referencedColumnName = "domains_id")
    @ManyToOne(optional = false)
    private Domains semesterId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peopleId")
    private Collection<Useri> useriCollection;

    public People() {
    }

    public People(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public People(Integer peopleId, String name, String firstLastname, String secondLastname, String email, Date age, String cellphone, String address, String ci, boolean status) {
        this.peopleId = peopleId;
        this.name = name;
        this.firstLastname = firstLastname;
        this.secondLastname = secondLastname;
        this.email = email;
        this.age = age;
        this.cellphone = cellphone;
        this.address = address;
        this.ci = ci;
        this.status = status;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public void setFirstLastname(String firstLastname) {
        this.firstLastname = firstLastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Domains getGenderId() {
        return genderId;
    }

    public void setGenderId(Domains genderId) {
        this.genderId = genderId;
    }

    public Domains getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Domains occupationId) {
        this.occupationId = occupationId;
    }

    public Domains getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Domains semesterId) {
        this.semesterId = semesterId;
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
        hash += (peopleId != null ? peopleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof People)) {
            return false;
        }
        People other = (People) object;
        if ((this.peopleId == null && other.peopleId != null) || (this.peopleId != null && !this.peopleId.equals(other.peopleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.People[ peopleId=" + peopleId + " ]";
    }

}
