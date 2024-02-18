package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class RetailItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private double quantity;

    @ManyToMany(mappedBy = "items")
    private List<Discount> discounts;

    public BigDecimal getDiscountedPriceForDate(LocalDateTime localDateTime) {
       Optional<Discount> discount = getDiscounts()
            .stream()
            .filter(disc -> !disc.getStartingFrom().isAfter(localDateTime) && disc.getEnds().isAfter(localDateTime))
            .findAny();
      return discount
          .map(value -> this.price.multiply(BigDecimal.valueOf(100).subtract(value.getPercentage())).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))
          .orElseGet(() -> this.price);
    }

    public void setQuantity(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot set quantity to value less than 0");
        }
        this.quantity = value;
    }
}
