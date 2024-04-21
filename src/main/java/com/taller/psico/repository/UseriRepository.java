package com.taller.psico.repository;

import com.taller.psico.entity.Useri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UseriRepository extends JpaRepository<Useri, Integer> {
    @Query("SELECT u FROM Useri u WHERE u.userName = ?1") // Query para buscar por nombre de usuario
    Optional<Useri> findByUserName(String username);  // Retorna un Optional

    //Mostrar el usuario por id
    @Query("SELECT u FROM Useri u WHERE u.userId = ?1")
    public Useri findByIdUser(Integer userId);


}
