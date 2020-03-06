package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void save(Product product);
    List<Product> findAllProductByStatus();
    Optional<Product> findProductById(Long id);
}
