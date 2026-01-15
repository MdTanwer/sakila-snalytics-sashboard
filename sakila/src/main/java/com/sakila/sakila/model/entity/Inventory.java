package com.sakila.analytics.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    
    @Id
    @Column(name = "inventory_id")
    private Long inventoryId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    
    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private Set<Rental> rentals;
}
