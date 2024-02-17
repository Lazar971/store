package com.milosavljevic.lazar.store.service;

import com.milosavljevic.lazar.store.dto.discount.DiscountDto;
import com.milosavljevic.lazar.store.dto.discount.WriteDiscountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface DiscountService {

  Page<DiscountDto> search(LocalDateTime date, Pageable pageable);

  DiscountDto findById(Long id);

  DiscountDto create(WriteDiscountDto dto);

  DiscountDto update(Long id, WriteDiscountDto dto);

  void delete(Long id);
}
