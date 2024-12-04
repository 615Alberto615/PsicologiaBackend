package com.taller.psico.repository;

import com.taller.psico.entity.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnalisisRepository extends JpaRepository<Analisis, Integer> {
    // Mostrar todos los análisis
    @Query(value = "SELECT * FROM risk_analysis", nativeQuery = true)
    List<Analisis> findAllAnalisis();

    // Obtener por ID
    @Query(value = "SELECT * FROM risk_analysis WHERE id = ?1", nativeQuery = true)
    Analisis findByIdAnalisis(Integer analisisId);

}
