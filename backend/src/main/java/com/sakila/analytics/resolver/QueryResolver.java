package com.sakila.analytics.resolver;

import com.sakila.analytics.model.dto.*;
import com.sakila.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QueryResolver {
    
    private final AnalyticsService analyticsService;
    
    /**
     * Parse date string to LocalDateTime
     * Input format: "YYYY-MM-DD"
     * Output: LocalDateTime at start of day (00:00:00)
     */
    private LocalDateTime parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return null;
        }
        return LocalDateTime.parse(dateStr + "T00:00:00");
    }
    
    @QueryMapping
    public List<FilmRentalDTO> getTopRentedFilms(
            @Argument Integer storeId,
            @Argument String startDate,
            @Argument String endDate) {
        
        log.info("GraphQL Query: getTopRentedFilms - storeId: {}, startDate: {}, endDate: {}", 
                 storeId, startDate, endDate);
        
        return analyticsService.getTopRentedFilms(
            storeId,
            parseDate(startDate),
            parseDate(endDate)
        );
    }
    
    @QueryMapping
    public List<CategoryRevenueDTO> getRevenueByCategory(
            @Argument Integer storeId,
            @Argument String startDate,
            @Argument String endDate) {
        
        log.info("GraphQL Query: getRevenueByCategory - storeId: {}, startDate: {}, endDate: {}", 
                 storeId, startDate, endDate);
        
        return analyticsService.getRevenueByCategory(
            storeId,
            parseDate(startDate),
            parseDate(endDate)
        );
    }
    
    @QueryMapping
    public List<CustomerStatsDTO> getTopCustomers(
            @Argument Integer storeId,
            @Argument String startDate,
            @Argument String endDate) {
        
        log.info("GraphQL Query: getTopCustomers - storeId: {}, startDate: {}, endDate: {}", 
                 storeId, startDate, endDate);
        
        return analyticsService.getTopCustomers(
            storeId,
            parseDate(startDate),
            parseDate(endDate)
        );
    }
    
    @QueryMapping
    public MetricsDTO getKeyMetrics(
            @Argument Integer storeId,
            @Argument String startDate,
            @Argument String endDate) {
        
        log.info("GraphQL Query: getKeyMetrics - storeId: {}, startDate: {}, endDate: {}", 
                 storeId, startDate, endDate);
        
        return analyticsService.getKeyMetrics(
            storeId,
            parseDate(startDate),
            parseDate(endDate)
        );
    }
    
    @QueryMapping
    public List<TransactionDTO> getRecentTransactions(
            @Argument Integer storeId,
            @Argument String startDate,
            @Argument String endDate,
            @Argument Integer limit) {
        
        // Use default limit if null
        int actualLimit = (limit != null) ? limit : 10;
        
        log.info("GraphQL Query: getRecentTransactions - storeId: {}, startDate: {}, endDate: {}, limit: {}", 
                 storeId, startDate, endDate, actualLimit);
        
        return analyticsService.getRecentTransactions(
            storeId,
            parseDate(startDate),
            parseDate(endDate),
            actualLimit
        );
    }
}
