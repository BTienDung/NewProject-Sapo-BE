package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void save(Category category);
    void delete(Long id);
    List<Category> findAllCategory();
    Optional<Category> findCategoryById(Long id);
}
