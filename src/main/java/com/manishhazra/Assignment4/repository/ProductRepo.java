package com.manishhazra.Assignment4.repository;

import com.manishhazra.Assignment4.model.Product;
import com.manishhazra.Assignment4.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findAllBySeason(Season season);
}