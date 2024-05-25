package com.taller.psico.repository;

import com.taller.psico.entity.Completion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, Long> {
    List<Completion> findBySessionId(String sessionId);
}
