package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    
    // Simple CRUD operations inherited from JpaRepository
    // Additional custom queries can be added here if needed
}
