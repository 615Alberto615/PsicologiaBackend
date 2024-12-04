package com.taller.psico.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "RISK_ANALYSIS")
public class Analisis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ACTIVO", nullable = false, length = 255)
    private String activo;

    @Column(name = "AMENAZA", nullable = false, length = 255)
    private String amenaza;

    @Column(name = "CONSECUENCIA", nullable = false, columnDefinition = "TEXT")
    private String consecuencia;

    @Column(name = "PROBABILIDAD", nullable = false, length = 50)
    private String probabilidad;

    @Column(name = "IMPACTO", nullable = false, length = 50)
    private String impacto;

    @Column(name = "RIESGO_INHERENTE", nullable = false, length = 50)
    private String riesgoInherente;

    @Column(name = "NIVEL_RIESGO", nullable = false, length = 50)
    private String nivelRiesgo;

    @Column(name = "TRATAMIENTO", columnDefinition = "TEXT")
    private String tratamiento;

    @Column(name = "CONTROLES", columnDefinition = "TEXT")
    private String controles;

    @Column(name = "TIPO", length = 100)
    private String tipo;

    @Column(name = "NIVEL", length = 50)
    private String nivel;

    @Column(name = "FRECUENCIA", length = 50)
    private String frecuencia;

    @Column(name = "PROBABILIDAD_RESIDUAL", length = 50)
    private String probabilidadResidual;

    @Column(name = "IMPACTO_RESIDUAL", length = 50)
    private String impactoResidual;

    @Column(name = "RIESGO_RESIDUAL", length = 50)
    private String riesgoResidual;

    @Column(name = "NIVEL_RIESGO_RESIDUAL", length = 50)
    private String nivelRiesgoResidual;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Analisis() {
    }

    public Analisis(Integer id, String activo, String amenaza, String consecuencia, String probabilidad, String impacto, String riesgoInherente, String nivelRiesgo, String controles, String tratamiento, String tipo, String frecuencia, String nivel, String probabilidadResidual, String impactoResidual, String riesgoResidual, String nivelRiesgoResidual, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.activo = activo;
        this.amenaza = amenaza;
        this.consecuencia = consecuencia;
        this.probabilidad = probabilidad;
        this.impacto = impacto;
        this.riesgoInherente = riesgoInherente;
        this.nivelRiesgo = nivelRiesgo;
        this.controles = controles;
        this.tratamiento = tratamiento;
        this.tipo = tipo;
        this.frecuencia = frecuencia;
        this.nivel = nivel;
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

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
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

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
        return "Analisis{" +
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
