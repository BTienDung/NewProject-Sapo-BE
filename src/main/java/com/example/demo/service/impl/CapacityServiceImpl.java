package com.example.demo.service.impl;

import com.example.demo.model.Capacity;
import com.example.demo.repository.CapacityRepository;
import com.example.demo.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapacityServiceImpl implements CapacityService {
    @Autowired
    private CapacityRepository capacityRepository;
    @Override
    public void save(Capacity capacity) {
        capacityRepository.save(capacity);
    }

    @Override
    public void delete(Long id) {
        capacityRepository.deleteById(id);
    }

    @Override
    public List<Capacity> findAllCapacity() {
        return capacityRepository.findAll();
    }

    @Override
    public Optional<Capacity> findByCapacityId(Long id) {
        return capacityRepository.findById(id);
    }
}
