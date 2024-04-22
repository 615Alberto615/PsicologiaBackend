package com.taller.psico.repository;

import com.taller.psico.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    //Ver todos los tratamientos de un usuario
    @Query("SELECT t FROM Treatment t WHERE t.userPatientId.userId= ?1")
    public List<Treatment> findAllByUserId(Integer userId);
}
