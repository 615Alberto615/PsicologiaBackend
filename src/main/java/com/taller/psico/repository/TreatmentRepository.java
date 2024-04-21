package com.taller.psico.repository;

import com.taller.psico.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}
