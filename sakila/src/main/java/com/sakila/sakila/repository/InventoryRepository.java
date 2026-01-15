package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    // Basic CRUD operations inherited from JpaRepository
    // Additional custom queries can be added here if needed
}
