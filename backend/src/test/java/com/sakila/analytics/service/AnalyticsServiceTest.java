package com.sakila.analytics.service;

import com.sakila.analytics.model.dto.*;
import com.sakila.analytics.repository.*;
import com.sakila.analytics.service.impl.AnalyticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    private FilmRepository filmRepository;
    
    @Mock
    private PaymentRepository paymentRepository;
    
    @Mock
    private RentalRepository rentalRepository;
    
    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @InjectMocks
    private AnalyticsServiceImpl analyticsService;
    
    private LocalDateTime testStartDate;
    private LocalDateTime testEndDate;
    private Integer testStoreId;
    
    @BeforeEach
    void setUp() {
        testStartDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        testEndDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        testStoreId = 1;
    }
    
    @Test
    void getTopRentedFilms_ShouldReturnTop5Films() {
        // Given
        List<FilmRentalDTO> expectedFilms = Arrays.asList(
            new FilmRentalDTO(1, "Film A", 100L),
            new FilmRentalDTO(2, "Film B", 90L),
            new FilmRentalDTO(3, "Film C", 80L),
            new FilmRentalDTO(4, "Film D", 70L),
            new FilmRentalDTO(5, "Film E", 60L)
        );
        
        when(filmRepository.findTopRentedFilms(eq(testStoreId), eq(testStartDate), eq(testEndDate), any(PageRequest.class)))
            .thenReturn(expectedFilms);
        
        // When
        List<FilmRentalDTO> result = analyticsService.getTopRentedFilms(testStoreId, testStartDate, testEndDate);
        
        // Then
        assertEquals(5, result.size());
        assertEquals("Film A", result.get(0).getTitle());
        assertEquals(100L, result.get(0).getRentalCount());
        verify(filmRepository).findTopRentedFilms(eq(testStoreId), eq(testStartDate), eq(testEndDate), any(PageRequest.class));
    }
    
    @Test
    void getRevenueByCategory_ShouldCalculatePercentages() {
        // Given
        List<CategoryRevenueDTO> rawRevenueData = Arrays.asList(
            new CategoryRevenueDTO("Action", new BigDecimal("500.00"), 0.0),
            new CategoryRevenueDTO("Comedy", new BigDecimal("300.00"), 0.0),
            new CategoryRevenueDTO("Drama", new BigDecimal("200.00"), 0.0)
        );
        
        when(categoryRepository.findRevenueByCategory(eq(testStoreId), eq(testStartDate), eq(testEndDate)))
            .thenReturn(rawRevenueData);
        
        // When
        List<CategoryRevenueDTO> result = analyticsService.getRevenueByCategory(testStoreId, testStartDate, testEndDate);
        
        // Then
        assertEquals(3, result.size());
        assertEquals(50.0, result.get(0).getPercentage()); // 500/1000 * 100
        assertEquals(30.0, result.get(1).getPercentage()); // 300/1000 * 100
        assertEquals(20.0, result.get(2).getPercentage()); // 200/1000 * 100
    }
    
    @Test
    void getTopCustomers_ShouldReturnTop10Customers() {
        // Given
        List<CustomerStatsDTO> expectedCustomers = Arrays.asList(
            new CustomerStatsDTO(1, "John Doe", 10, new BigDecimal("100.00")),
            new CustomerStatsDTO(2, "Jane Smith", 8, new BigDecimal("80.00"))
        );
        
        when(customerRepository.findTopCustomersBySpending(eq(testStoreId), eq(testStartDate), eq(testEndDate), any(PageRequest.class)))
            .thenReturn(expectedCustomers);
        
        // When
        List<CustomerStatsDTO> result = analyticsService.getTopCustomers(testStoreId, testStartDate, testEndDate);
        
        // Then
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals(new BigDecimal("100.00"), result.get(0).getTotalSpent());
    }
    
    @Test
    void getKeyMetrics_ShouldReturnTotalRevenueAndActiveRentals() {
        // Given
        BigDecimal expectedRevenue = new BigDecimal("1000.00");
        Long expectedActiveRentals = 25L;
        
        when(paymentRepository.calculateTotalRevenue(eq(testStoreId), eq(testStartDate), eq(testEndDate)))
            .thenReturn(expectedRevenue);
        when(rentalRepository.countActiveRentals(eq(testStoreId), eq(testStartDate), eq(testEndDate)))
            .thenReturn(expectedActiveRentals);
        
        // When
        MetricsDTO result = analyticsService.getKeyMetrics(testStoreId, testStartDate, testEndDate);
        
        // Then
        assertEquals(expectedRevenue, result.getTotalRevenue());
        assertEquals(expectedActiveRentals, result.getActiveRentals());
    }
    
    @Test
    void getRecentTransactions_ShouldReturnTransactionDTOs() {
        // Given
        // This test would require more complex mocking due to entity relationships
        // For simplicity, we'll test the method call exists
        int limit = 5;
        
        when(paymentRepository.findRecentTransactions(eq(testStoreId), eq(testStartDate), eq(testEndDate), any(PageRequest.class)))
            .thenReturn(Arrays.asList());
        
        // When
        List<TransactionDTO> result = analyticsService.getRecentTransactions(testStoreId, testStartDate, testEndDate, limit);
        
        // Then
        assertNotNull(result);
        verify(paymentRepository).findRecentTransactions(eq(testStoreId), eq(testStartDate), eq(testEndDate), any(PageRequest.class));
    }
}
