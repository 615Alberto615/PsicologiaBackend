package com.taller.psico.repository;

import com.taller.psico.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    //Ver todas las recetas de un avance clinico
    @Query("SELECT a FROM Prescription a WHERE a.clinicalAdvancesId.clinicalAdvancesId = ?1")
    public List<Prescription> findAllByClinicalAdvancesId(Integer clinicalAdvancesId);
}
