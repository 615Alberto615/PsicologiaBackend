package com.taller.psico.repository;

import com.taller.psico.entity.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnalisisRepository extends JpaRepository<Analisis, Integer> {
    //Mostrar todos los analisis
    @Query(value = "SELECT * FROM analisis", nativeQuery = true)
    List<Analisis> findAllAnalisis();

    //Obtener por id
    @Query(value = "SELECT * FROM analisis WHERE analisis_id = ?1", nativeQuery = true)
    Analisis findByIdAnalisis(Integer analisisId);
}
