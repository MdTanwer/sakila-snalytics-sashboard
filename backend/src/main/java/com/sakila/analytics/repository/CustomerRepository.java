package com.sakila.analytics.repository;

import com.sakila.analytics.model.dto.CustomerStatsDTO;
import com.sakila.analytics.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    /**
     * Get top customers by total spending
     */
    @Query("SELECT new com.sakila.analytics.model.dto.CustomerStatsDTO(" +
           "c.customerId, " +
           "CONCAT(c.firstName, ' ', c.lastName), " +
           "COUNT(DISTINCT r.rentalId), " +
           "COALESCE(SUM(p.amount), 0)) " +
           "FROM Customer c " +
           "LEFT JOIN c.store s " +
           "LEFT JOIN Rental r ON c.customerId = r.customer.customerId " +
           "LEFT JOIN Payment p ON r.rentalId = p.rental.rentalId " +
           "WHERE (:storeId IS NULL OR s.storeId = :storeId) " +
           "AND (:startDate IS NULL OR p.paymentDate >= :startDate) " +
           "AND (:endDate IS NULL OR p.paymentDate <= :endDate) " +
           "GROUP BY c.customerId, c.firstName, c.lastName " +
           "ORDER BY COALESCE(SUM(p.amount), 0) DESC")
    List<CustomerStatsDTO> findTopCustomersBySpending(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        org.springframework.data.domain.Pageable pageable
    );
}
