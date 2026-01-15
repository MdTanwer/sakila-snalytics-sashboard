package com.sakila.analytics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRevenueDTO {
    private String category;
    private BigDecimal revenue;
    private Double percentage;  // Calculated field
}
