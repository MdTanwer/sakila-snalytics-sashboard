package com.sakila.analytics.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Set;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    
    @Id
    @Column(name = "film_id")
    private Integer filmId;
    
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "release_year")
    private Year releaseYear;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    
    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;
    
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private java.math.BigDecimal rentalRate;
    
    @Column(name = "length")
    private Integer length;
    
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private java.math.BigDecimal replacementCost;
    
    @Column(name = "rating", length = 5)
    private String rating;
    
    @Column(name = "special_features", columnDefinition = "TEXT")
    private String specialFeatures;
    
    @Column(name = "last_update", nullable = false)
    private java.time.LocalDateTime lastUpdate;
    
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private Set<Inventory> inventories;
    
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private Set<FilmCategory> filmCategories;
}
