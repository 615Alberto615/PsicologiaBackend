package com.taller.psico.repository;

import com.taller.psico.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
}
