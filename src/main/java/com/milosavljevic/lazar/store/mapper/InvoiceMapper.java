package com.milosavljevic.lazar.store.mapper;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.InvoiceItemDto;
import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.entity.Invoice;
import com.milosavljevic.lazar.store.entity.InvoiceItem;
import com.milosavljevic.lazar.store.entity.RetailItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface InvoiceMapper {

  @Mapping(target = "items", expression = "java(this.toInvoiceItemsDto(invoice.getInvoiceItems()))")
  InvoiceDto toDto(Invoice invoice);

  @Mapping(target = "items", ignore = true)
  InvoiceDto toBaseDto(Invoice invoice);

  List<InvoiceItemDto> toInvoiceItemsDto(List<InvoiceItem> invoiceItems);

  @Mapping(target = "discountedPrice", ignore = true)
  RetailItemDto toRetailItemDto(RetailItem retailItem);
}
