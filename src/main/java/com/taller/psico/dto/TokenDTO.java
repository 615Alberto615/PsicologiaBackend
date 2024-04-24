package com.taller.psico.dto;

public class TokenDTO {

    private String token;
    private Integer id;
    private Integer rol;

    public TokenDTO() {
    }

    public TokenDTO(String token, Integer id, Integer rol) {
        this.token = token;
        this.id = id;
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", rol=" + rol +
                '}';
    }
}
