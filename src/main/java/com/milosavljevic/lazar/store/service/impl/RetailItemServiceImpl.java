package com.milosavljevic.lazar.store.service.impl;

import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.dto.retailItem.WriteRetailItemDto;
import com.milosavljevic.lazar.store.entity.RetailItem;
import com.milosavljevic.lazar.store.mapper.RetailItemMapper;
import com.milosavljevic.lazar.store.repository.RetailItemRepository;
import com.milosavljevic.lazar.store.service.RetailItemService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RetailItemServiceImpl implements RetailItemService {
    private final RetailItemMapper retailItemMapper;
    private  final RetailItemRepository retailItemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<RetailItemDto> searchItems(@Nullable  String search, Pageable pageable) {
        return retailItemRepository.searchItems(search == null ? "" : search, pageable).map(retailItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public RetailItemDto fetchById(Long id) {
        return retailItemMapper.toDto(this.findItemById(id));
    }

    @Override
    @Transactional
    public RetailItemDto create(WriteRetailItemDto writeRetailItemDto) {
        RetailItem retailItem = retailItemMapper.fromDto(writeRetailItemDto);
        retailItem = retailItemRepository.save(retailItem);
        return retailItemMapper.toDto(retailItem);
    }

    @Override
    @Transactional
    public RetailItemDto update(Long id, WriteRetailItemDto writeRetailItemDto) {
        RetailItem retailItem = findItemById(id);
        retailItemMapper.updateRetailItemFromDto(writeRetailItemDto, retailItem);
        return retailItemMapper.toDto(retailItem);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        RetailItem retailItem = findItemById(id);
        retailItemRepository.delete(retailItem);
    }

    private RetailItem findItemById(Long id){
        return retailItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Missing item for given id"));
    }
}
