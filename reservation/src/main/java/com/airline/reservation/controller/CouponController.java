package com.airline.reservation.controller;

import com.airline.reservation.entity.Coupon;
import com.airline.reservation.entity.Reservation;
import com.airline.reservation.service.CouponService;
import com.airline.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ReservationService reservationService;

    @PutMapping("/apply")
    public ResponseEntity<?> applyCoupon(@RequestBody Map<String, Object> requestData) {
        Long reservationId = Long.valueOf(requestData.get("reservationId").toString());
        String couponCode = requestData.get("couponCode").toString();
        try {
            Reservation reservation = couponService.applyCoupon(reservationId, couponCode);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/apply")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        return ResponseEntity.ok(couponService.getAllCoupons());
    }
}
