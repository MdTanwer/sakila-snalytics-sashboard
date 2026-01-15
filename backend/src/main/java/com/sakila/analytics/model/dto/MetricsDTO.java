package com.sakila.analytics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricsDTO {
    private BigDecimal totalRevenue;
    private Long activeRentals;
}
