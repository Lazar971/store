package com.milosavljevic.lazar.store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime issuanceDate;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems;


    public void removeItem(Long itemId) {
        Optional<InvoiceItem> invoiceItem = this.invoiceItems.stream()
            .filter(item -> item.getId().equals(itemId)).findAny();
        if(invoiceItem.isEmpty()) {
            return;
        }
        invoiceItem.get().getRetailItem()
            .setQuantity(invoiceItem.get().getAmount() + invoiceItem.get().getRetailItem().getQuantity());
        invoiceItems.remove(invoiceItem.get());
    }

    public void removeAllItems() {
        for (InvoiceItem invoiceItem : invoiceItems) {
            invoiceItem.getRetailItem()
                .setQuantity(invoiceItem.getAmount() + invoiceItem.getRetailItem().getQuantity());
        }
        invoiceItems.clear();
    }

    public void saveItem(RetailItem retailItem, double quantity) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice(this);
        invoiceItem.setRetailItem(retailItem);
        invoiceItem.setAmount(quantity);
        invoiceItem.setUnitPrice(retailItem.getDiscountedPriceForDate(this.issuanceDate));
        retailItem.setQuantity(retailItem.getQuantity() - quantity);
    }
}
