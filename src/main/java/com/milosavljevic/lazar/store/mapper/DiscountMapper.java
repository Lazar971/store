package com.milosavljevic.lazar.store.mapper;

import com.milosavljevic.lazar.store.dto.discount.DiscountDto;
import com.milosavljevic.lazar.store.dto.discount.WriteDiscountDto;
import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.entity.Discount;
import com.milosavljevic.lazar.store.entity.RetailItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper
public interface DiscountMapper {

  DiscountDto toDto(Discount discount);

  @Mapping(target = "items", ignore = true)
  DiscountDto toBaseDto(Discount discount);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "items", ignore = true)
  Discount fromDto(WriteDiscountDto dto);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "items", ignore = true)
  void updateDiscountFromDto(WriteDiscountDto dto, @MappingTarget Discount discount);

  @Mapping(target = "discountedPrice", ignore = true)
  Set<RetailItemDto> toRetailItemDto(Set<RetailItem> retailItems);
}
