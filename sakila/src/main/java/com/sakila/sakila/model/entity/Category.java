package com.sakila.sakila.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<FilmCategory> filmCategories;
}
