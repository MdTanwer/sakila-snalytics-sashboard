package com.sakila.analytics.repository;

import com.sakila.analytics.model.dto.FilmRentalDTO;
import com.sakila.analytics.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    
    /**
     * Get top rented films with rental counts
     */
    @Query("SELECT new com.sakila.analytics.model.dto.FilmRentalDTO(" +
           "f.filmId, f.title, COUNT(r.rentalId)) " +
           "FROM Film f " +
           "JOIN f.inventories i " +
           "JOIN i.rentals r " +
           "WHERE (:storeId IS NULL OR i.store.storeId = :storeId) " +
           "AND (:startDate IS NULL OR r.rentalDate >= :startDate) " +
           "AND (:endDate IS NULL OR r.rentalDate <= :endDate) " +
           "GROUP BY f.filmId, f.title " +
           "ORDER BY COUNT(r.rentalId) DESC")
    List<FilmRentalDTO> findTopRentedFilms(
        @Param("storeId") Integer storeId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
        org.springframework.data.domain.Pageable pageable
    );
}
