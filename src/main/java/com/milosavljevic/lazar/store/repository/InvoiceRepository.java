package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

  @Query("""
      SELECT i FROM Invoice i
      WHERE (:start IS NULL OR o.issuanceDate > :start) AND
            (:end IS NULL OR o.issuanceDate < :end)
      """)
  Page<Invoice> searchInvoices(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
