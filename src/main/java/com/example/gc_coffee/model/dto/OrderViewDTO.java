package com.example.gc_coffee.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderViewDTO {
    private Long orderId;
    private String email;
    private String address;
    private String zipCode;
    private LocalDateTime orderDateTime;
    private boolean status; // 0:false, 1:true
    private String coffeeName;
    private int coffeePrice;
    private int quantity;
    private int totalPrice; // orders 테이블의 total_price
}
