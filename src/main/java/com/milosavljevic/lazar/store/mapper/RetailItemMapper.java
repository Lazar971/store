package com.milosavljevic.lazar.store.mapper;

import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.dto.retailItem.WriteRetailItemDto;
import com.milosavljevic.lazar.store.entity.RetailItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(imports = {LocalDateTime.class})
public interface RetailItemMapper {

    @Mapping(target = "discountedPrice", expression = "java(retailItem.getDiscountedPriceForDate(LocalDateTime.now()))")
    RetailItemDto toDto(RetailItem retailItem);

    @Mapping(target = "id", ignore = true)
    RetailItem fromDto(WriteRetailItemDto retailItemDto);
    @Mapping(target = "id", ignore = true)
    void updateRetailItemFromDto(WriteRetailItemDto retailItemDto, @MappingTarget RetailItem retailItem);
}
