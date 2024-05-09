package com.taller.psico.repository;

import com.taller.psico.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    //Todos los roles
    @Query("SELECT r FROM Rol r")
    public List<Rol> findAllRoles();

    //Devolver el rol por el nombre
    @Query("SELECT r FROM Rol r WHERE r.rolType = ?1")
    public Rol findByName(String name);

}
