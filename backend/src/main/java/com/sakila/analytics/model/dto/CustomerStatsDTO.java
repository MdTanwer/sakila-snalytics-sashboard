package com.sakila.analytics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatsDTO {
    private Integer customerId;
    private String fullName;
    private Integer totalRentals;
    private BigDecimal totalSpent;
}
