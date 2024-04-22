package com.taller.psico.repository;

import com.taller.psico.entity.ClinicalAdvances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicalAdvancesRepository extends JpaRepository<ClinicalAdvances, Integer> {
    //Ver todos los avances clinicos de un tratamiento
    @Query("SELECT a FROM ClinicalAdvances a WHERE a.treatmentId.treatmentId = ?1")
    public List<ClinicalAdvances> findAllByTreatmentId(Integer treatmentId);
}
