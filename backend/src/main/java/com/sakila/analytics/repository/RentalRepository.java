package com.sakila.analytics.repository;

import com.sakila.analytics.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    
    /**
     * Count active rentals (not yet returned)
     * Active rental = return_date IS NULL
     */
    @Query("SELECT COUNT(r) FROM Rental r " +
           "WHERE r.returnDate IS NULL " +
           "AND (:storeId IS NULL OR r.inventory.store.storeId = :storeId) " +
           "AND (:startDate IS NULL OR r.rentalDate >= :startDate) " +
           "AND (:endDate IS NULL OR r.rentalDate <= :endDate)")
    Long countActiveRentals(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
