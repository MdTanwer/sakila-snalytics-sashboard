package com.sakila.sakila.controller;

import com.sakila.sakila.model.dto.StoreMetrics;
import com.sakila.sakila.model.entity.Film;
import com.sakila.sakila.repository.FilmRepository;
import com.sakila.sakila.repository.PaymentRepository;
import com.sakila.sakila.repository.RentalRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FilmController {

    private final FilmRepository filmRepository;
    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;

    public FilmController(FilmRepository filmRepository, 
                       PaymentRepository paymentRepository,
                       RentalRepository rentalRepository) {
        this.filmRepository = filmRepository;
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
    }

    @QueryMapping
    public List<Film> films() {
        return filmRepository.findAll();
    }

    @QueryMapping
    public Film filmById(@Argument Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public StoreMetrics getStoreMetrics(
            @Argument Long storeId,
            @Argument String dateFrom,
            @Argument String dateTo) {
        
        // Parse dates with defaults (use very broad range for sakila sample data)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = dateFrom != null ? LocalDateTime.parse(dateFrom + "T00:00:00") : 
                               LocalDateTime.of(2000, 1, 1, 0, 0); // Very broad start date
        LocalDateTime endDate = dateTo != null ? LocalDateTime.parse(dateTo + "T23:59:59") : 
                             LocalDateTime.now(); // Current date for "to"
        
        // Calculate total revenue using repository method
        BigDecimal totalRevenue = paymentRepository.getTotalRevenueByDateRangeAndStore(startDate, endDate, storeId);
        
        // Calculate active rentals using repository method
        Long activeRentals = rentalRepository.countActiveRentalsByDateRangeAndStore(startDate, endDate, storeId);
        
        // Return StoreMetrics object
        return new StoreMetrics(
            totalRevenue,
            activeRentals,
            storeId,
            startDate.format(dateFormatter),
            endDate.format(dateFormatter)
        );
    }
}
