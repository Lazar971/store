package com.milosavljevic.lazar.store.controller;

import com.milosavljevic.lazar.store.dto.retailItem.RetailItemDto;
import com.milosavljevic.lazar.store.dto.retailItem.WriteRetailItemDto;
import com.milosavljevic.lazar.store.service.RetailItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/retail-items")
@AllArgsConstructor
public class RetailItemController {
    private final RetailItemService retailItemService;

    @GetMapping
    public Page<RetailItemDto> searchItem(@RequestParam(required = false, name = "search") String name,
                                          Pageable pageable){
        return retailItemService.searchItems(name, pageable);
    }

    @GetMapping("/{id}")
    public  RetailItemDto getById(@PathVariable Long id){
        return retailItemService.fetchById(id);
    }

    @PostMapping
    public RetailItemDto create(@Valid @RequestBody WriteRetailItemDto dto) {
        return retailItemService.create(dto);
    }

    @PutMapping("/{id}")
    public RetailItemDto update(@PathVariable Long id,@Valid @RequestBody WriteRetailItemDto dto) {
        return retailItemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        retailItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
