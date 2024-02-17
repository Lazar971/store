package com.milosavljevic.lazar.store.service;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.InvoiceItemDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface InvoiceService {

  Page<InvoiceDto> searchInvoices(LocalDateTime start, LocalDateTime end, Pageable pageable);

  InvoiceDto findById(Long id);

  InvoiceDto create(WriteInvoiceDto dto);

  InvoiceDto update(Long id, WriteInvoiceDto dto);

  void delete(Long id);
}
