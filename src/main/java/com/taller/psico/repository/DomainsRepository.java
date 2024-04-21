package com.taller.psico.repository;

import com.taller.psico.entity.Domains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainsRepository extends JpaRepository<Domains, Integer> {

    //Todos los dominios de un tipo
    @Query("SELECT d FROM Domains d WHERE d.type = ?1")
    public List<Domains> findByType(String type);
}
