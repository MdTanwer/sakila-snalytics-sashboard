package com.sakila.analytics.service;

import com.sakila.analytics.model.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsService {
    
    List<FilmRentalDTO> getTopRentedFilms(Integer storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<CategoryRevenueDTO> getRevenueByCategory(Integer storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<CustomerStatsDTO> getTopCustomers(Integer storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    MetricsDTO getKeyMetrics(Integer storeId, LocalDateTime startDate, LocalDateTime endDate);
    
    List<TransactionDTO> getRecentTransactions(Integer storeId, LocalDateTime startDate, LocalDateTime endDate, int limit);
}
