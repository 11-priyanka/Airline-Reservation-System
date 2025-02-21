package com.airline.reservation.controller;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/fare/{flightId}")
    public ResponseEntity<?> getFare(@PathVariable Long flightId) {
        Optional<Flight> flight = flightService.getFare(flightId);
        if (flight.isPresent()) {
            return ResponseEntity.ok(flight.get());
        } else {
            return ResponseEntity.status(404).body("{\"message\": \"Flight ID " + flightId + " not found.\"}");
        }
    }
}

