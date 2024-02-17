package com.milosavljevic.lazar.store.service.impl;

import com.milosavljevic.lazar.store.dto.discount.DiscountDto;
import com.milosavljevic.lazar.store.dto.discount.WriteDiscountDto;
import com.milosavljevic.lazar.store.entity.Discount;
import com.milosavljevic.lazar.store.entity.RetailItem;
import com.milosavljevic.lazar.store.mapper.DiscountMapper;
import com.milosavljevic.lazar.store.repository.DiscountRepository;
import com.milosavljevic.lazar.store.repository.RetailItemRepository;
import com.milosavljevic.lazar.store.service.DiscountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {
  private final DiscountMapper discountMapper;
  private final DiscountRepository discountRepository;
  private final RetailItemRepository retailItemRepository;
  @Override
  @Transactional
  public Page<DiscountDto> search(LocalDateTime date, Pageable pageable) {
    return discountRepository.searchByDate(date, pageable).map(this.discountMapper::toBaseDto);
  }

  @Override
  @Transactional
  public DiscountDto findById(Long id) {
    return this.discountMapper.toDto(this.findOrThrow(id));
  }

  private Discount findOrThrow(Long id){
  return discountRepository.findById(id)
      .orElseThrow(()-> new IllegalArgumentException("Missing discount with given id"));
  }

  @Override
  @Transactional
  public DiscountDto create(WriteDiscountDto dto) {
    Discount discount = this.discountMapper.fromDto(dto);
    discount.setItems(this.fetchItemsForIds(dto.getItemIds()));
    return this.discountMapper.toDto(discountRepository.save(discount));
  }

  @Override
  @Transactional
  public DiscountDto update(Long id, WriteDiscountDto dto) {
    Discount discount = this.findOrThrow(id);
    this.discountMapper.updateDiscountFromDto(dto, discount);
    discount.setItems(this.fetchItemsForIds(dto.getItemIds()));
    return this.discountMapper.toDto(discount);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Discount discount = this.findOrThrow(id);
    this.discountRepository.delete(discount);
  }

  private List<RetailItem> fetchItemsForIds(List<Long> ids){
    List<RetailItem> items = this.retailItemRepository.findAllById(ids);
    if (items.size() < ids.size()) {
      throw new IllegalArgumentException("Some id is missing");
    }
    return items;
  }
}