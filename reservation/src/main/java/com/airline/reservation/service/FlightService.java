package com.airline.reservation.service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flight -> {
                    Flight flightWithoutFare = new Flight(
                            flight.getFlightId(),
                            flight.getAirline(),
                            flight.getOrigin(),
                            flight.getDestination(),
                            flight.getDepartureTime(),
                            flight.getArrivalTime(),
                            null
                    );
                    return flightWithoutFare;
                }).toList();
    }

    public Optional<Flight> getFare(Long flightId) {
        return flightRepository.findById(flightId);
    }
}
