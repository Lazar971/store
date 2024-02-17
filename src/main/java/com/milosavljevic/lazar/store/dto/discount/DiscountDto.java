package com.milosavljevic.lazar.store.dto.discount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiscountDto {
  private Long id;
  private String name;
  private BigDecimal percentage;
  private LocalDateTime startingFrom;
  private LocalDateTime ends;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<RetailItemDto> items;
}
