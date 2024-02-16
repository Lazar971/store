package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Invoice {
    @Id
    private Long id;
    private LocalDateTime issuanceDate;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems;
}
