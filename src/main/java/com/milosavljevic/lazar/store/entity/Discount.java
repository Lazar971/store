package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal percentage;
    private LocalDateTime startingFrom;
    private LocalDateTime ends;

    @ManyToMany(targetEntity = RetailItem.class, cascade = {CascadeType.ALL})
    private List<RetailItem> items;
}
