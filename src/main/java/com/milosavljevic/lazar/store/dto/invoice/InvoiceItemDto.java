package com.milosavljevic.lazar.store.dto.invoice;

import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemDto {
  private Long id;
  private Double amount;
  private BigDecimal unitPrice;
  private RetailItemDto retailItem;
}
