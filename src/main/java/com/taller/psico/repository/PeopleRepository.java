package com.taller.psico.repository;

import com.taller.psico.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {
}
