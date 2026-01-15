package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    
    // Simple CRUD operations inherited from JpaRepository
    // Additional custom queries can be added here if needed
}
