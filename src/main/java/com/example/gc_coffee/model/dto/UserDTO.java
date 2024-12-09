package com.example.gc_coffee.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    //데이터 베이스의 데이터를 담아서 전달
    private String email;
    private String zipcode;
    private String address;
    private int totalPrice;
    LocalDateTime orderDateTime;
    private Long orderId;
    private int quantity;

}
