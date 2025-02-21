package com.airline.reservation.controller;

import com.airline.reservation.entity.Reservation;
import com.airline.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody Map<String, Object> request) {
        try {
            String passengerName = (String) request.get("passengerName");
            String email = (String) request.get("email");
            Long flightId = Long.valueOf(request.get("flightId").toString());

            Reservation reservation = reservationService.createReservation(flightId, passengerName, email);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    @PutMapping("/confirm/{reservationId}")
    public ResponseEntity<?> confirmReservation(@PathVariable Long reservationId) {
        try {
            Reservation confirmedReservation = reservationService.confirmReservation(reservationId);
            return ResponseEntity.ok(confirmedReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}