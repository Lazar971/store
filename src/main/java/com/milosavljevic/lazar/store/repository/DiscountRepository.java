package com.milosavljevic.lazar.store.repository;

import com.milosavljevic.lazar.store.entity.Discount;
import com.milosavljevic.lazar.store.entity.RetailItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

  @Query("""
      SELECT d FROM Discount d
      WHERE :localDateTime IS NULL OR :localDateTime BETWEEN d.startingFrom AND d.ends
    """)
  Page<Discount> searchByDate(LocalDateTime localDateTime, Pageable pageable);

  @Query("""
        SELECT d.id FROM Discount d JOIN d.items i
        WHERE i IN (:retailItems) AND
        (
          (d.startingFrom BETWEEN :start AND :end) OR
          (d.ends BETWEEN :start AND :end) OR
          (:start BETWEEN d.startingFrom AND d.ends) OR
          (:end BETWEEN d.startingFrom AND d.ends)
        )
      """)
  Set<Long> findDiscountIdsForIntervalAndItems(LocalDateTime start, LocalDateTime end, List<RetailItem> retailItems);
}
