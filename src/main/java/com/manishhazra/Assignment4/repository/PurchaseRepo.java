package com.manishhazra.Assignment4.repository;

import com.manishhazra.Assignment4.model.Purchase;
import com.manishhazra.Assignment4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}