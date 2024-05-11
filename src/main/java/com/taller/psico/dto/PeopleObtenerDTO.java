package com.taller.psico.dto;

import java.util.Date;

public class PeopleObtenerDTO {
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
    private DomainsDTO genderId;  // Solo el ID de género
    private DomainsDTO occupationId;  // Solo el ID de ocupación
    private DomainsDTO semesterId;  // Solo el ID de semestre

    // Constructor vacío
    public PeopleObtenerDTO() {
    }

    // Constructor con todos los campos


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

    public DomainsDTO getGenderId() {
        return genderId;
    }

    public void setGenderId(DomainsDTO genderId) {
        this.genderId = genderId;
    }

    public DomainsDTO getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(DomainsDTO occupationId) {
        this.occupationId = occupationId;
    }

    public DomainsDTO getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(DomainsDTO semesterId) {
        this.semesterId = semesterId;
    }
}
