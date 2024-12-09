package com.example.gc_coffee.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoffeeDTO {
    //데이터 베이스의 데이터를 담아서 전달
    private Long coffeeId; // 고유 아이디
    private String coffeeName;
    private int coffeePrice;
}