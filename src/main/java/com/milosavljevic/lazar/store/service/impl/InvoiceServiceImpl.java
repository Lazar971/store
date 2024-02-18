package com.milosavljevic.lazar.store.service.impl;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceItemDto;
import com.milosavljevic.lazar.store.entity.Invoice;
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
    this.saveItems(invoice, dto.getItems());
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
    invoice.removeAllItems();
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
    if(retailItems.size() < itemDtoList.size()) {
      throw new IllegalArgumentException("Missing some retail item");
    }
    return retailItems;
  }

  private void saveItems(Invoice invoice, List<WriteInvoiceItemDto> itemDtoList) {
    this.validateItemsQuantity(itemDtoList);
    List<RetailItem> retailItems = this.fetchRetailItems(itemDtoList);
    for (RetailItem retailItem: retailItems) {
      Optional<WriteInvoiceItemDto> invoiceItemDto = itemDtoList.stream()
          .filter(item -> item.getRetailItemId() == retailItem.getId())
          .findAny();
      if (invoiceItemDto.isEmpty()) {
        throw new IllegalArgumentException("Missing invoice item for retail item id " + retailItem.getId());
      }
      invoice.saveItem(retailItem, invoiceItemDto.get().getQuantity());
    }
  }
}
