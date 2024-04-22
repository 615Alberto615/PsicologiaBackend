package com.taller.psico.repository;

import com.taller.psico.entity.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Integer> {
    @Query("SELECT p FROM People p JOIN p.useriCollection u WHERE u.rolId.rolId = :rolId")
    Page<People> findPeopleByRoleId(Integer rolId, Pageable pageable);

}