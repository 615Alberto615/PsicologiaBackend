package com.taller.psico.repository;

import com.taller.psico.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvailabilityRepotisory extends JpaRepository<Availability, Integer> {
    List<Availability> findByUserIdUserId(int userId);


}