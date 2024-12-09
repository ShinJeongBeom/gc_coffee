package com.example.gc_coffee.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    //데이터 베이스의 데이터를 담아서 전달
    private Long orderId; // 자동 생성된 값을 읽기 위해 사용
    private String email;
    private String address;
    private String zipCode;
    LocalDateTime orderDateTime; //주문 시간을 받아서 전날 14시 - 당일 14시까지 출고처리
    private boolean status; //상태를 추가하여 0이면 아직 출고 안됨 1이면 출고완료
}
