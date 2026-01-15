package com.sakila.analytics.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    
    @Id
    @Column(name = "store_id")
    private Integer storeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaff;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<Customer> customers;
    
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<Inventory> inventories;
}
