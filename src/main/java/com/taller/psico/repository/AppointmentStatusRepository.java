package com.taller.psico.repository;

import com.taller.psico.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Integer> {
}
