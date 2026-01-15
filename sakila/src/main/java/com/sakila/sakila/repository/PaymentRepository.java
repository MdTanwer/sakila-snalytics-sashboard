package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Find payments by date range and store
    @Query("SELECT p FROM Payment p WHERE " +
           "p.paymentDate >= :startDate AND p.paymentDate <= :endDate AND " +
           "(:storeId IS NULL OR p.rental.inventory.store.storeId = :storeId)")
    List<Payment> findByDateRangeAndStore(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("storeId") Long storeId
    );
    
    // Get total revenue for date range and store (simplified query)
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE " +
           "p.paymentDate >= :startDate AND p.paymentDate <= :endDate AND " +
           "(:storeId IS NULL OR p.rental.inventory.store.storeId = :storeId)")
    BigDecimal getTotalRevenueByDateRangeAndStore(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        @Param("storeId") Long storeId
    );
    
    // Get total revenue for date range only (no store filter)
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE " +
           "p.paymentDate >= :startDate AND p.paymentDate <= :endDate")
    BigDecimal getTotalRevenueByDateRange(
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
