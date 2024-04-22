package com.taller.psico.repository;

import com.taller.psico.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Integer> {

    //Ver todas las observaciones de un avance clinico
    @Query("SELECT a FROM Observation a WHERE a.clinicalAdvancesId.clinicalAdvancesId = ?1")
    public List<Observation> findAllByClinicalAdvancesId(Integer clinicalAdvancesId);
}
