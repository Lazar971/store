package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.RetailItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetailItemRepository extends JpaRepository<RetailItem, Long> {

    @Query("SELECT i FROM RetailItem i WHERE i.name LIKE %:search%")
    Page<RetailItem> searchItems(String search, Pageable pageable);
}
