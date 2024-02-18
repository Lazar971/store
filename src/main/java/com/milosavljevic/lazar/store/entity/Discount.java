package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
        name = "discount_item",
        joinColumns = {@JoinColumn(name = "discount_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "retail_item_id", referencedColumnName = "id")}
    )
    private Set<RetailItem> items;
}
