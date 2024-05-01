package com.taller.psico.repository;

import com.taller.psico.entity.Quotes;
import com.taller.psico.entity.Useri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuotesRepository extends JpaRepository<Quotes, Integer> {
    @Query("SELECT q FROM Quotes q WHERE q.userId = :user")
    List<Quotes> findByUser(Useri user);

    // Corregido para acceder a la propiedad 'availabilityId' dentro del objeto 'Availability' asociado
    List<Quotes> findByAvailabilityId_AvailabilityIdAndStatus(Integer availabilityId, boolean status);

    @Query("SELECT q FROM Quotes q WHERE q.availabilityId.userId.userId = :therapistId")
    List<Quotes> findByTherapistId(int therapistId);
}
