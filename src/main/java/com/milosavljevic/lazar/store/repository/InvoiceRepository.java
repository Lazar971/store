package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
