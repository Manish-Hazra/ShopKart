package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.model.Purchase;

import java.util.UUID;

public interface PurchaseService {
    public Purchase purchase(UUID id, String username);
}
