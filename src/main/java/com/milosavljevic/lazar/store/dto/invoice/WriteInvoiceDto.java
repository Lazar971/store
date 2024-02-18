package com.milosavljevic.lazar.store.dto.invoice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WriteInvoiceDto {
  @NotNull
  private LocalDateTime issuanceDate;
  @NotNull
  @NotEmpty
  private List<WriteInvoiceItemDto> items;
}
