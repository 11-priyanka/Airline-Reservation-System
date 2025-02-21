package com.airline.reservation.service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.Reservation;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Reservation createReservation(Long flightId, String passengerName, String email) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if (flight.isPresent()) {
            Reservation reservation = new Reservation(passengerName, email, flight.get(), false, null);
            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Flight not found");
        }
    }

    public Reservation confirmReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            if (reservation.isConfirmed()) {
                throw new RuntimeException("Reservation is already confirmed");
            }
            reservation.setConfirmed(true);
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation Id not found"));
    }

    public Reservation confirmBooking(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setConfirmed(true);
            return reservationRepository.save(reservation);
        }).orElse(null);
    }
}
