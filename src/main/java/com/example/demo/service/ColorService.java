package com.example.demo.service;

import com.example.demo.model.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    void save(Color color);
    void delete(Long id);
    List<Color> findAllColor();
    Optional<Color> findByColorId(Long id);
}
