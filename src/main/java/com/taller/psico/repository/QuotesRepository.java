package com.taller.psico.repository;

import com.taller.psico.entity.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotesRepository extends JpaRepository<Quotes, Integer> {
}
