package com.taller.psico.dto;

import java.io.Serializable;

public class DomainsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer domainsId;
    private String type;
    private String name;
    private String description;

    // Constructor vacío
    public DomainsDTO() {
    }

    // Constructor con todos los campos
    public DomainsDTO(Integer domainsId, String type, String name, String description) {
        this.domainsId = domainsId;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    // Getters y Setters
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

    // Método toString
    @Override
    public String toString() {
        return "DomainsDTO{" +
                "domainsId=" + domainsId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
