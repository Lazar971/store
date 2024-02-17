package com.milosavljevic.lazar.store.dto.retailItem;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RetailItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Double quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal discountedPrice;
}
