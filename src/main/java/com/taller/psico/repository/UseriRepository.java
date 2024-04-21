package com.taller.psico.repository;

import com.taller.psico.entity.Useri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UseriRepository extends JpaRepository<Useri, Integer> {
    Optional<Useri> findByUserName(String username);  // Retorna un Optional
}
