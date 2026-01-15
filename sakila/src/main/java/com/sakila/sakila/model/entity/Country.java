package com.sakila.sakila.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    
    @Id
    @Column(name = "country_id")
    private Long countryId;
    
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
