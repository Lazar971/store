package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Discount {
    @Id
    private Long id;
    private String name;
    private BigDecimal percentage;
    private LocalDateTime startingFrom;
    private LocalDateTime ends;

    @ManyToMany(targetEntity = RetailItem.class, cascade = {CascadeType.ALL})
    private List<RetailItem> items;
}
