package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

  @Query("""
      SELECT d FROM Discount d
      WHERE :localDateTime IS NULL OR :localDateTime BETWEEN d.startingFrom AND d.ends
""")
  Page<Discount> searchByDate(LocalDateTime localDateTime, Pageable pageable);
}
