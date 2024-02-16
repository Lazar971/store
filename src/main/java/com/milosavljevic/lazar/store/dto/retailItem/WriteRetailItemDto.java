package com.milosavljevic.lazar.store.dto.retailItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WriteRetailItemDto {
    @NotNull
    private String name;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @Min(0)
    @NotNull
    private Double quantity;
}
