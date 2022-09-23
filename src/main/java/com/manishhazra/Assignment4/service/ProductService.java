package com.manishhazra.Assignment4.service;

import com.manishhazra.Assignment4.dto.ProductDto;
import com.manishhazra.Assignment4.model.Product;

import java.util.List;

public interface ProductService {
    public Product save(ProductDto dto);
    public List<Product> listApparel(String email);
    public List<Product> listApparel();
}
