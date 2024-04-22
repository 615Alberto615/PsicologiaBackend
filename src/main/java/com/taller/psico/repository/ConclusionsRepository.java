package com.taller.psico.repository;

import com.taller.psico.entity.Conclusions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConclusionsRepository extends JpaRepository<Conclusions, Integer> {

    //Ver todas las conclusiones de un Post tratamiento
    @Query("SELECT a FROM Conclusions a WHERE a.postTreatmentId.postTreatmentId = ?1")
    public List<Conclusions> findAllByPostTreatmentId(Integer postTreatmentId);
}
