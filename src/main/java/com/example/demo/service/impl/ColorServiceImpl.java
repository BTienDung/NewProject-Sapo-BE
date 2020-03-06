package com.example.demo.service.impl;

import com.example.demo.model.Color;
import com.example.demo.repository.ColorRepository;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }

    @Override
    public List<Color> findAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findByColorId(Long id) {
        return colorRepository.findById(id);
    }
}
