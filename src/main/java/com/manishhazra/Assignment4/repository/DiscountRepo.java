package com.manishhazra.Assignment4.repository;

import com.manishhazra.Assignment4.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountRepo extends JpaRepository<Discount, Long> {
    Discount findByApparel_Id(UUID id);
}