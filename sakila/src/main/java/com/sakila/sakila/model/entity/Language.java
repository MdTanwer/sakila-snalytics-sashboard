package com.sakila.sakila.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "language")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    
    @Id
    @Column(name = "language_id")
    private Long languageId;
    
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private Set<Film> films;
    
    @OneToMany(mappedBy = "originalLanguage", fetch = FetchType.LAZY)
    private Set<Film> originalLanguageFilms;
}
