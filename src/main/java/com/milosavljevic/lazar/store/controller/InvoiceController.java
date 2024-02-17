package com.milosavljevic.lazar.store.controller;

import com.milosavljevic.lazar.store.dto.invoice.InvoiceDto;
import com.milosavljevic.lazar.store.dto.invoice.WriteInvoiceDto;
import com.milosavljevic.lazar.store.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {
  private final InvoiceService invoiceService;

  @GetMapping
  public Page<InvoiceDto> searchInvoices(
      @RequestParam(name = "start", required = false)LocalDateTime start,
      @RequestParam(name = "end", required = false) LocalDateTime end,
      Pageable pageable
  ) {
    return this.invoiceService.searchInvoices(start, end, pageable);
  }

  @GetMapping("/{id}")
  public InvoiceDto findById(@PathVariable Long id) {
    return this.invoiceService.findById(id);
  }

  @PostMapping
  public InvoiceDto create(@Valid @RequestBody WriteInvoiceDto dto) {
    return this.invoiceService.create(dto);
  }

  @PutMapping("/{id}")
  public InvoiceDto update(@PathVariable Long id,@Valid @RequestBody WriteInvoiceDto dto) {
    return this.invoiceService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.invoiceService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
