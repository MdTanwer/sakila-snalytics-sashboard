package com.sakila.sakila.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    
    @Id
    @Column(name = "rental_id")
    private Long rentalId;
    
    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;  // NULL = still rented (active rental)
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
