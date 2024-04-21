package com.taller.psico.dto;

import java.io.Serializable;
import java.util.Date;

public class PeopleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer peopleId;
    private String photo;
    private String name;
    private String firstLastname;
    private String secondLastname;
    private String email;
    private Date age;
    private String cellphone;
    private String address;
    private String ci;
    private boolean status;
    private Integer genderId;  // Solo el ID de género
    private Integer occupationId;  // Solo el ID de ocupación
    private Integer semesterId;  // Solo el ID de semestre

    // Constructor vacío
    public PeopleDTO() {
    }

    // Constructor con todos los campos
    public PeopleDTO(Integer peopleId, String photo, String name, String firstLastname, String secondLastname, String email, Date age, String cellphone, String address, String ci, boolean status, Integer genderId, Integer occupationId, Integer semesterId) {
        this.peopleId = peopleId;
        this.photo = photo;
        this.name = name;
        this.firstLastname = firstLastname;
        this.secondLastname = secondLastname;
        this.email = email;
        this.age = age;
        this.cellphone = cellphone;
        this.address = address;
        this.ci = ci;
        this.status = status;
        this.genderId = genderId;
        this.occupationId = occupationId;
        this.semesterId = semesterId;
    }

    // Getters y Setters
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

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Integer occupationId) {
        this.occupationId = occupationId;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    // Método toString
    @Override
    public String toString() {
        return "PeopleDTO{" +
                "peopleId=" + peopleId +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", firstLastname='" + firstLastname + '\'' +
                ", secondLastname='" + secondLastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", cellphone='" + cellphone + '\'' +
                ", address='" + address + '\'' +
                ", ci='" + ci + '\'' +
                ", status=" + status +
                ", genderId=" + genderId +
                ", occupationId=" + occupationId +
                ", semesterId=" + semesterId +
                '}';
    }
}
