package com.sakila.analytics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentalDTO {
    private Integer filmId;
    private String title;
    private Long rentalCount;
}
