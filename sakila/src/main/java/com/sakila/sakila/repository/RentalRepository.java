package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    
    // Find rentals by date range and store
    @Query("SELECT r FROM Rental r WHERE " +
           "r.rentalDate >= :startDate AND r.rentalDate <= :endDate AND " +
           "(:storeId IS NULL OR r.inventory.store.storeId = :storeId)")
    List<Rental> findByDateRangeAndStore(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("storeId") Long storeId
    );
    
    // Count active rentals (not returned) by date range and store (simplified query)
    @Query("SELECT COUNT(r) FROM Rental r WHERE " +
           "r.rentalDate >= :startDate AND r.rentalDate <= :endDate AND " +
           "r.returnDate IS NULL AND " +
           "(:storeId IS NULL OR r.inventory.store.storeId = :storeId)")
    Long countActiveRentalsByDateRangeAndStore(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("storeId") Long storeId
    );
    
    // Count active rentals by date range only (no store filter)
    @Query("SELECT COUNT(r) FROM Rental r WHERE " +
           "r.rentalDate >= :startDate AND r.rentalDate <= :endDate AND " +
           "r.returnDate IS NULL")
    Long countActiveRentalsByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
