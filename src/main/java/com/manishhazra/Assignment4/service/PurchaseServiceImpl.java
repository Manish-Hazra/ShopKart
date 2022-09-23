package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.repository.ProductRepo;
import com.manishhazra.Assignment4.repository.PurchaseRepo;
import com.manishhazra.Assignment4.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final ProductRepo apparelRepository;
    private final UserRepo userRepository;
    private final PurchaseRepo purchaseRepository;

    public PurchaseServiceImpl(ProductRepo apparelRepository, UserRepo userRepository, PurchaseRepo purchaseRepository) {
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase purchase(UUID apparelId, String username) {
        Purchase purchase = new Purchase();
        purchase.setApparel(apparelRepository.getById(apparelId));
        purchase.setUser(userRepository.findByEmail(username));
        return purchaseRepository.save(purchase);
    }
}
