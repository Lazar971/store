package com.milosavljevic.lazar.store.service;

import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.dto.retailItem.WriteRetailItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RetailItemService {

    Page<RetailItemDto> searchItems(String name, Pageable pageable);

    RetailItemDto fetchById(Long id);

    RetailItemDto create(WriteRetailItemDto writeRetailItemDto);
    RetailItemDto update(Long id, WriteRetailItemDto writeRetailItemDto);
    void delete(Long id);
}
