package com.milosavljevic.lazar.store.service;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceItemDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface InvoiceService {

  Page<InvoiceItemDto> searchInvoices(LocalDateTime start, LocalDateTime end);

  InvoiceItemDto findById(Long id);

  InvoiceItemDto create(WriteInvoiceDto dto);

  InvoiceItemDto update(Long id, WriteInvoiceDto dto);

  void delete(Long id);
}
