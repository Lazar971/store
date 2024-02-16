package com.milosavljevic.lazar.store.dto.retailItem;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RetailItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Double quantity;
    private BigDecimal discountedPrice;
}
