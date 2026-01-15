package com.sakila.analytics.repository;

import com.sakila.analytics.model.dto.CategoryRevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<com.sakila.analytics.model.entity.Category, Integer> {
    
    /**
     * Get revenue by film category
     */
    @Query("SELECT new com.sakila.analytics.model.dto.CategoryRevenueDTO(" +
           "cat.name, " +
           "COALESCE(SUM(p.amount), 0), " +
           "0.0) " +
           "FROM Category cat " +
           "JOIN cat.filmCategories fc " +
           "JOIN fc.film f " +
           "JOIN f.inventories i " +
           "JOIN i.rentals r " +
           "JOIN Payment p ON r.rentalId = p.rental.rentalId " +
           "WHERE (:storeId IS NULL OR i.store.storeId = :storeId) " +
           "AND (:startDate IS NULL OR p.paymentDate >= :startDate) " +
           "AND (:endDate IS NULL OR p.paymentDate <= :endDate) " +
           "GROUP BY cat.categoryId, cat.name " +
           "ORDER BY COALESCE(SUM(p.amount), 0) DESC")
    List<CategoryRevenueDTO> findRevenueByCategory(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
