package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
}
