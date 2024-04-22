package com.taller.psico.repository;

import com.taller.psico.entity.PostTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostTreatmentRepository extends JpaRepository<PostTreatment, Integer> {

    //Ver todos los tratamientos de un tratamiento
    @Query("SELECT a FROM PostTreatment a WHERE a.treatmentId.treatmentId = ?1")
    public PostTreatment findAllByTreatmentId(Integer treatmentId);

}
