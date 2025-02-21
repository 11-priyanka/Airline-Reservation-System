package com.airline.reservation.config;

import com.airline.reservation.entity.Coupon;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.repository.CouponRepository;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FlightRepository flightRepository, ReservationRepository reservationRepository, CouponRepository couponRepository) {
        return args -> {
            flightRepository.save(new Flight("Air India", "Delhi", "Mumbai", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4), new BigDecimal("5000")));
            flightRepository.save(new Flight("IndiGo", "Bangalore", "Chennai", LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5), new BigDecimal("4000")));
            flightRepository.save(new Flight("SpiceJet", "Hyderabad", "Pune", LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(6), new BigDecimal("4500")));
            flightRepository.save(new Flight("GoAir", "Kolkata", "Delhi", LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(7), new BigDecimal("6000")));
            flightRepository.save(new Flight("Vistara", "Ahmedabad", "Jaipur", LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(8), new BigDecimal("5500")));
            couponRepository.save(new Coupon("DISCOUNT10", new BigDecimal("500")));
            couponRepository.save(new Coupon("DISCOUNT15", new BigDecimal("600")));
        };
    }
}
