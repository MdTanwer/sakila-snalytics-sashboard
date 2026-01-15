package com.sakila.analytics.repository;

import com.sakila.analytics.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    /**
     * Calculate total revenue with optional filters
     * Returns NULL if no payments found
     */
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p " +
           "WHERE (:storeId IS NULL OR p.rental.inventory.store.storeId = :storeId) " +
           "AND (:startDate IS NULL OR p.paymentDate >= :startDate) " +
           "AND (:endDate IS NULL OR p.paymentDate <= :endDate)")
    BigDecimal calculateTotalRevenue(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    /**
     * Get recent transactions with customer and film details
     */
    @Query("SELECT p FROM Payment p " +
           "JOIN FETCH p.customer c " +
           "JOIN FETCH p.rental r " +
           "JOIN FETCH r.inventory i " +
           "JOIN FETCH i.film f " +
           "WHERE (:storeId IS NULL OR i.store.storeId = :storeId) " +
           "AND (:startDate IS NULL OR p.paymentDate >= :startDate) " +
           "AND (:endDate IS NULL OR p.paymentDate <= :endDate) " +
           "ORDER BY p.paymentDate DESC")
    List<Payment> findRecentTransactions(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        org.springframework.data.domain.Pageable pageable
    );
}
