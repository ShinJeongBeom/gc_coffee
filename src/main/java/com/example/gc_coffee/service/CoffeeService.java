package com.example.gc_coffee.service;

import com.example.gc_coffee.model.dto.CoffeeDTO;
import com.example.gc_coffee.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public List<CoffeeDTO> getAllCoffeeList(){
        return coffeeRepository.selectAllCoffee();
    }

    public CoffeeDTO getCoffeeById(long coffeeId) {
        return coffeeRepository.selectCoffeeById(coffeeId);
    }


}