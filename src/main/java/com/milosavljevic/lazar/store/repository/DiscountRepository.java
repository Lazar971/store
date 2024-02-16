package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
