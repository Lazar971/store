package com.milosavljevic.lazar.store.dto.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
  private Long id;
  private LocalDateTime issuanceDate;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<InvoiceItemDto> items;
}
