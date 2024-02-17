package com.milosavljevic.lazar.store.dto.invoice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WriteInvoiceItemDto {
  @NotNull
  private long retailItemId;
  @NotNull
  @Min(0)
  private double quantity;
}
