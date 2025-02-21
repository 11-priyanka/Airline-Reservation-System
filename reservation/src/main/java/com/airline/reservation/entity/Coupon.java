package com.airline.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BigDecimal discount;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Coupon(String code, BigDecimal discount) {
        this.code = code;
        this.discount = discount;
    }

    public Coupon() {
    }
}
