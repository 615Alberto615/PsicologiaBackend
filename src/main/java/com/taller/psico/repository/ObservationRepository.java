package com.taller.psico.repository;

import com.taller.psico.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Integer> {
}
