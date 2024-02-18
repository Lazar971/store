package com.milosavljevic.lazar.store.dto.discount;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WriteDiscountDto {
  @NotNull
  private String name;
  @NotNull
  @Min(0)
  @Max(100)
  private BigDecimal percentage;
  @NotNull
  private LocalDateTime startingFrom;
  @NotNull
  private LocalDateTime ends;
  @NotEmpty
  private List<Long> itemIds;
}
