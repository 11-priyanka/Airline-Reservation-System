package com.airline.reservation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationID;
    private String passengerName;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    private Flight flight;
    private boolean isConfirmed;
    private BigDecimal discountedFare;

    // Getters and Setters
    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isConfirmed() {
        return isConfirmed;
    }

    @JsonProperty("status")
    public String getStatus() {
        return isConfirmed ? "Confirmed" : "Not Confirmed Yet";
    }

    public String getDiscountedFare() {
        return discountedFare != null ? discountedFare.toString() : "Not applied";
    }

    public void setDiscountedFare(BigDecimal discountedFare) {
        this.discountedFare = discountedFare;
    }

    public Reservation(String passengerName, String email, Flight flight, boolean isConfirmed) {
        this.passengerName = passengerName;
        this.email = email;
        this.flight = flight;
        this.isConfirmed = isConfirmed;
    }

    public Reservation(String passengerName, String email, Flight flight, boolean isConfirmed, BigDecimal discountedFare) {
        this.passengerName = passengerName;
        this.email = email;
        this.flight = flight;
        this.isConfirmed = isConfirmed;
        this.discountedFare = discountedFare;
    }

    public Reservation() {
    }
}
