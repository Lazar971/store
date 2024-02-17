package com.milosavljevic.lazar.store.service.impl;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceItemDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import com.milosavljevic.lazar.store.mapper.InvoiceMapper;
import com.milosavljevic.lazar.store.repository.InvoiceRepository;
import com.milosavljevic.lazar.store.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
  private final InvoiceRepository invoiceRepository;
  private final InvoiceMapper invoiceMapper;
  @Override
  public Page<InvoiceItemDto> searchInvoices(LocalDateTime start, LocalDateTime end) {
    return null;
  }

  @Override
  public InvoiceItemDto findById(Long id) {
    return null;
  }

  @Override
  public InvoiceItemDto create(WriteInvoiceDto dto) {
    return null;
  }

  @Override
  public InvoiceItemDto update(Long id, WriteInvoiceDto dto) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
