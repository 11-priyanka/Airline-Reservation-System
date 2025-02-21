package com.airline.reservation.service;

import com.airline.reservation.entity.Coupon;
import com.airline.reservation.entity.Reservation;
import com.airline.reservation.repository.CouponRepository;
import com.airline.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Optional<Coupon> getCouponByCode(String code) {
        return couponRepository.findAll()
                .stream()
                .filter(coupon -> coupon.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    @Transactional
    public Reservation applyCoupon(Long reservationId, String couponCode) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            Coupon coupon = getCouponByCode(couponCode)
                    .orElseThrow(() -> new RuntimeException("Invalid coupon code"));

            BigDecimal discountedFare = reservation.getFlight().getFare().subtract(coupon.getDiscount());
            reservation.setDiscountedFare(discountedFare);
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation Id not found"));
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

}
