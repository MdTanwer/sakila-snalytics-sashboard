package com.sakila.sakila.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreMetrics {
    
    private BigDecimal totalRevenue;
    private Long activeRentals;
    private Long storeId;
    private String dateFrom;
    private String dateTo;
}
