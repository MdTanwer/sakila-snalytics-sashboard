package com.sakila.analytics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatsDTO {
    private Long customerId;
    private String fullName;
    private Long totalRentals;
    private BigDecimal totalSpent;
}
