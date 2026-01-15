package com.sakila.analytics.service.impl;

import com.sakila.analytics.model.dto.*;
import com.sakila.analytics.repository.*;
import com.sakila.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AnalyticsServiceImpl implements AnalyticsService {
    
    private final FilmRepository filmRepository;
    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    @Override
    public List<FilmRentalDTO> getTopRentedFilms(Integer storeId, LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Fetching top rented films - storeId: {}, startDate: {}, endDate: {}", 
                  storeId, startDate, endDate);
        
        return filmRepository.findTopRentedFilms(
            storeId, 
            startDate, 
            endDate, 
            PageRequest.of(0, 5)  // Top 5 films
        );
    }
    
    @Override
    public List<CategoryRevenueDTO> getRevenueByCategory(Integer storeId, LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Calculating revenue by category - storeId: {}, startDate: {}, endDate: {}", 
                  storeId, startDate, endDate);
        
        // Get raw revenue data from repository
        List<CategoryRevenueDTO> categoryRevenues = categoryRepository.findRevenueByCategory(
            storeId, startDate, endDate
        );
        
        // Calculate total revenue to compute percentages
        BigDecimal totalRevenue = categoryRevenues.stream()
            .map(CategoryRevenueDTO::getRevenue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Calculate percentage for each category
        if (totalRevenue.compareTo(BigDecimal.ZERO) > 0) {
            categoryRevenues.forEach(dto -> {
                double percentage = dto.getRevenue()
                    .divide(totalRevenue, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    .doubleValue();
                dto.setPercentage(Math.round(percentage * 100.0) / 100.0);  // Round to 2 decimals
            });
        }
        
        return categoryRevenues;
    }
    
    @Override
    public List<CustomerStatsDTO> getTopCustomers(Integer storeId, LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Fetching top customers - storeId: {}, startDate: {}, endDate: {}", 
                  storeId, startDate, endDate);
        
        return customerRepository.findTopCustomersBySpending(
            storeId, 
            startDate, 
            endDate, 
            PageRequest.of(0, 10)  // Top 10 customers
        );
    }
    
    @Override
    public MetricsDTO getKeyMetrics(Integer storeId, LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Calculating key metrics - storeId: {}, startDate: {}, endDate: {}", 
                  storeId, startDate, endDate);
        
        // Calculate total revenue
        BigDecimal totalRevenue = paymentRepository.calculateTotalRevenue(storeId, startDate, endDate);
        
        // Count active rentals (not yet returned)
        Long activeRentals = rentalRepository.countActiveRentals(storeId, startDate, endDate);
        
        return new MetricsDTO(totalRevenue, activeRentals);
    }
    
    @Override
    public List<TransactionDTO> getRecentTransactions(Integer storeId, LocalDateTime startDate, LocalDateTime endDate, int limit) {
        log.debug("Fetching recent transactions - storeId: {}, startDate: {}, endDate: {}, limit: {}", 
                  storeId, startDate, endDate, limit);
        
        return paymentRepository.findRecentTransactions(
            storeId, 
            startDate, 
            endDate, 
            PageRequest.of(0, limit)
        ).stream().map(payment -> new TransactionDTO(
            payment.getPaymentId(),
            payment.getCustomer().getFirstName() + " " + payment.getCustomer().getLastName(),
            payment.getRental().getInventory().getFilm().getTitle(),
            payment.getAmount(),
            payment.getPaymentDate().format(DATE_FORMATTER)
        )).collect(Collectors.toList());
    }
}
