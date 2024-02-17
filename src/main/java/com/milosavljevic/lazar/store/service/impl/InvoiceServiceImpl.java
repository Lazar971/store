package com.milosavljevic.lazar.store.service.impl;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.InvoiceItemDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceItemDto;
import com.milosavljevic.lazar.store.entity.Invoice;
import com.milosavljevic.lazar.store.entity.InvoiceItem;
import com.milosavljevic.lazar.store.entity.RetailItem;
import com.milosavljevic.lazar.store.mapper.InvoiceMapper;
import com.milosavljevic.lazar.store.repository.InvoiceRepository;
import com.milosavljevic.lazar.store.repository.RetailItemRepository;
import com.milosavljevic.lazar.store.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
  private final InvoiceRepository invoiceRepository;
  private final InvoiceMapper invoiceMapper;
  private final RetailItemRepository retailItemRepository;
  @Override
  @Transactional(readOnly = true)
  public Page<InvoiceDto> searchInvoices(LocalDateTime start, LocalDateTime end, Pageable pageable) {
    return invoiceRepository.searchInvoices(start, end, pageable).map(this.invoiceMapper::toBaseDto);
  }

  @Override
  @Transactional(readOnly = true)
  public InvoiceDto findById(Long id) {
    return this.invoiceMapper.toDto(this.findOrThrow(id));
  }

  @Override
  @Transactional
  public InvoiceDto create(WriteInvoiceDto dto) {
    Invoice invoice = new Invoice();
    invoice.setIssuanceDate(dto.getIssuanceDate());
    this.validateItemsQuantity(dto.getItems());
    List<RetailItem> retailItems = this.fetchRetailItems(dto.getItems());
    List<InvoiceItem> invoiceItems = new LinkedList<>();

    for (RetailItem retailItem: retailItems) {
      Optional<WriteInvoiceItemDto> invoiceItemDto = dto.getItems().stream()
          .filter(item -> item.getRetailItemId() == retailItem.getId())
          .findAny();
      if (invoiceItemDto.isEmpty() || invoiceItemDto.get().getQuantity() > retailItem.getQuantity()) {
        throw new IllegalArgumentException("Not enough quantity for retail item " + retailItem.getId());
      }
      InvoiceItem invoiceItem = new InvoiceItem();
      invoiceItem.setInvoice(invoice);
      invoiceItem.setRetailItem(retailItem);
      invoiceItem.setAmount(invoiceItemDto.get().getQuantity());
      invoiceItem.setUnitPrice(retailItem.getDiscountedPriceForDate(dto.getIssuanceDate()));

      retailItem.setQuantity(retailItem.getQuantity() - invoiceItem.getAmount());
    }

    invoice.setInvoiceItems(invoiceItems);
    return this.invoiceMapper.toDto(invoiceRepository.save(invoice));
  }

  @Override
  @Transactional
  public InvoiceDto update(Long id, WriteInvoiceDto dto) {
    return null;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Invoice invoice = findOrThrow(id);
    for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
      invoiceItem.getRetailItem().setQuantity(invoiceItem.getAmount() + invoiceItem.getRetailItem().getQuantity());
    }
    invoiceRepository.delete(invoice);
  }

  private Invoice findOrThrow(Long id) {
    return this.invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Missing invoice for given id"));
  }

  private void validateItemsQuantity(List<WriteInvoiceItemDto> itemDtoList) {
    if (itemDtoList.stream().anyMatch(dto -> dto.getQuantity() <=0 )) {
      throw new IllegalArgumentException("Invalid quantity");
    }
  }
  private List<RetailItem> fetchRetailItems(List<WriteInvoiceItemDto> itemDtoList) {
    List<RetailItem> retailItems = retailItemRepository.findAllById(itemDtoList.stream()
        .map(WriteInvoiceItemDto::getRetailItemId).toList());
    if(retailItems.size() <= itemDtoList.size()) {
      throw new IllegalArgumentException("Missing some retail item");
    }
    return retailItems;
  }
}
