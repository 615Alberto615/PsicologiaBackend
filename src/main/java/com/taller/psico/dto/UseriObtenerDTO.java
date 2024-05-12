package com.taller.psico.dto;

public class UseriObtenerDTO {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userName;
    private String password;  // Consider encryption or hashing in real scenarios
    private boolean status;
    private PeopleObtenerDTO people;  // Objeto completo PeopleDTO en lugar de solo el ID
    private Integer rolId;

    // Constructor vacío
    public UseriObtenerDTO() {
    }



    // Getters y setters
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

    public PeopleObtenerDTO getPeople() {
        return people;
    }

    public void setPeople(PeopleObtenerDTO people) {
        this.people = people;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }


}
