package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class InvoiceItem {
    @Id
    private Long id;
    private Double amount;
    private BigDecimal unitPrice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RetailItem retailItem;
}
