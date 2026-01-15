package com.sakila.sakila.repository;

import com.sakila.sakila.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Simple CRUD operations inherited from JpaRepository
    // Additional custom queries can be added here if needed
}
