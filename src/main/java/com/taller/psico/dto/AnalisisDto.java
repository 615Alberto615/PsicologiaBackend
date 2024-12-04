package com.taller.psico.dto;

import java.time.LocalDateTime;

public class AnalisisDto {

    private Integer id;
    private String activo;
    private String amenaza;
    private String consecuencia;
    private String probabilidad;
    private String impacto;
    private String riesgoInherente;
    private String nivelRiesgo;
    private String tratamiento;
    private String controles;
    private String tipo;
    private String nivel;
    private String frecuencia;
    private String probabilidadResidual;
    private String impactoResidual;
    private String riesgoResidual;
    private String nivelRiesgoResidual;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnalisisDto() {
    }

    public AnalisisDto(Integer id, String activo, String amenaza, String consecuencia, String probabilidad, String impacto, String riesgoInherente, String nivelRiesgo, String tratamiento, String controles, String tipo, String nivel, String frecuencia, String probabilidadResidual, String impactoResidual, String riesgoResidual, String nivelRiesgoResidual, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.activo = activo;
        this.amenaza = amenaza;
        this.consecuencia = consecuencia;
        this.probabilidad = probabilidad;
        this.impacto = impacto;
        this.riesgoInherente = riesgoInherente;
        this.nivelRiesgo = nivelRiesgo;
        this.tratamiento = tratamiento;
        this.controles = controles;
        this.tipo = tipo;
        this.nivel = nivel;
        this.frecuencia = frecuencia;
        this.probabilidadResidual = probabilidadResidual;
        this.impactoResidual = impactoResidual;
        this.riesgoResidual = riesgoResidual;
        this.nivelRiesgoResidual = nivelRiesgoResidual;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(String amenaza) {
        this.amenaza = amenaza;
    }

    public String getConsecuencia() {
        return consecuencia;
    }

    public void setConsecuencia(String consecuencia) {
        this.consecuencia = consecuencia;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getRiesgoInherente() {
        return riesgoInherente;
    }

    public void setRiesgoInherente(String riesgoInherente) {
        this.riesgoInherente = riesgoInherente;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getControles() {
        return controles;
    }

    public void setControles(String controles) {
        this.controles = controles;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getProbabilidadResidual() {
        return probabilidadResidual;
    }

    public void setProbabilidadResidual(String probabilidadResidual) {
        this.probabilidadResidual = probabilidadResidual;
    }

    public String getImpactoResidual() {
        return impactoResidual;
    }

    public void setImpactoResidual(String impactoResidual) {
        this.impactoResidual = impactoResidual;
    }

    public String getRiesgoResidual() {
        return riesgoResidual;
    }

    public void setRiesgoResidual(String riesgoResidual) {
        this.riesgoResidual = riesgoResidual;
    }

    public String getNivelRiesgoResidual() {
        return nivelRiesgoResidual;
    }

    public void setNivelRiesgoResidual(String nivelRiesgoResidual) {
        this.nivelRiesgoResidual = nivelRiesgoResidual;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AnalisisDto{" +
                "id=" + id +
                ", activo='" + activo + '\'' +
                ", amenaza='" + amenaza + '\'' +
                ", consecuencia='" + consecuencia + '\'' +
                ", probabilidad='" + probabilidad + '\'' +
                ", impacto='" + impacto + '\'' +
                ", riesgoInherente='" + riesgoInherente + '\'' +
                ", nivelRiesgo='" + nivelRiesgo + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", controles='" + controles + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nivel='" + nivel + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", probabilidadResidual='" + probabilidadResidual + '\'' +
                ", impactoResidual='" + impactoResidual + '\'' +
                ", riesgoResidual='" + riesgoResidual + '\'' +
                ", nivelRiesgoResidual='" + nivelRiesgoResidual + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
