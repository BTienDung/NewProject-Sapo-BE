package com.example.demo.service;

import com.example.demo.model.Capacity;

import java.util.List;
import java.util.Optional;

public interface CapacityService {
    void save(Capacity capacity);
    void delete(Long id);
    List<Capacity> findAllCapacity();
    Optional<Capacity> findByCapacityId(Long id);
}
