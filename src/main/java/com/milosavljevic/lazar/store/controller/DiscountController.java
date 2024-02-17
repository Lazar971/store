package com.milosavljevic.lazar.store.controller;

import com.milosavljevic.lazar.store.dto.discount.DiscountDto;
import com.milosavljevic.lazar.store.dto.discount.WriteDiscountDto;
import com.milosavljevic.lazar.store.service.DiscountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/discounts")
@AllArgsConstructor
public class DiscountController {
    private DiscountService discountService;

    @GetMapping
    public Page<DiscountDto> searchDiscounts(
        @RequestParam(name = "date", required = false) LocalDateTime date,
        Pageable pageable) {
        return this.discountService.search(date, pageable);
    }

    @GetMapping("/{id}")
    public DiscountDto findById(@PathVariable Long id) {
        return discountService.findById(id);
    }

    @PostMapping
    public DiscountDto create(@Valid @RequestBody WriteDiscountDto dto) {
        return discountService.create(dto);
    }

    @PutMapping("/{id}")
    public  DiscountDto update(@PathVariable Long id, @Valid @RequestBody WriteDiscountDto dto) {
        return discountService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        discountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
