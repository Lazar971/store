package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private BigDecimal unitPrice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RetailItem retailItem;
}
