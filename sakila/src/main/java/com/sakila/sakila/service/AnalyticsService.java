package com.sakila.analytics.service;

import com.sakila.analytics.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsService {
    
    List<FilmRentalDTO> getTopRentedFilms(Long storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<CategoryRevenueDTO> getRevenueByCategory(Long storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<CustomerStatsDTO> getTopCustomers(Long storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    MetricsDTO getKeyMetrics(Long storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<TransactionDTO> getRecentTransactions(Long storeId, LocalDateTime startDate, LocalDateTime endDate, int limit);
}
