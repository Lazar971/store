package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private Long id;
    private String name;
    private BigDecimal price;
    private Double quantity;

    @ManyToMany(mappedBy = "items")
    private List<Discount> discounts;


    public BigDecimal getDiscountedPriceForDate(LocalDateTime localDateTime){
       Optional<Discount> discount = this.discounts
            .stream()
            .filter(disc -> !disc.getStartingFrom().isAfter(localDateTime) && disc.getEnds().isAfter(localDateTime))
            .findAny();
      return discount
          .map(value -> this.price.multiply(value.getPercentage()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))
          .orElseGet(() -> this.price);
    }
}
