package com.example.gc_coffee.repository;

import com.example.gc_coffee.model.dto.CoffeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoffeeRepository {
    List<CoffeeDTO> selectAllCoffee();
    int getCoffeePriceById(long coffeeId);

    // 커피 상세 조회용 메서드 추가
    CoffeeDTO selectCoffeeById(long coffeeId);
}