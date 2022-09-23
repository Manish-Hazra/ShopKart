package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.DiscountDto;
import com.manishhazra.Assignment4.model.Discount;

import java.util.UUID;

public interface DiscountService {
    public double getDiscountedPrice(UUID apparelId);
    public Discount save(DiscountDto dealDto);
}
