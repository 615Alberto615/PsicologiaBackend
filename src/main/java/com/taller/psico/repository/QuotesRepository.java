package com.taller.psico.repository;

import com.taller.psico.entity.Quotes;
import com.taller.psico.entity.Useri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface QuotesRepository extends JpaRepository<Quotes, Integer> {

    @Query("SELECT q FROM Quotes q WHERE q.userId = :user")
    List<Quotes> findByUser(Useri user);

    // Fetch all active quotes for a specific availability
    List<Quotes> findByAvailabilityId_AvailabilityIdAndStatus(Integer availabilityId, boolean status);

    // Todos las quiotes por fecha y id de avalability
    @Query("SELECT q FROM Quotes q WHERE q.availabilityId.availabilityId = :availabilityId AND q.startTime = :fecha")
    List<Quotes> findByAvailabilityIdAndDate(int availabilityId, Date fecha);

    @Query("SELECT q FROM Quotes q WHERE q.availabilityId.userId.userId = :therapistId")
    List<Quotes> findByTherapistId(int therapistId);

    // Count the number of active quotes for a specific therapist's availability
    @Query("SELECT count(q) FROM Quotes q WHERE q.availabilityId.availabilityId = :availabilityId AND q.status = :status")
    long countByAvailabilityIdAndStatus(int availabilityId, boolean status);

    //obtener todas las citas con estado terminado
    @Query("SELECT q FROM Quotes q WHERE q.status = false")
    List<Quotes> findByStatusFalse();

}
