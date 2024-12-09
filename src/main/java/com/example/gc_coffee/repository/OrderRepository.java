package com.example.gc_coffee.repository;
import com.example.gc_coffee.model.dto.OrderDTO;
import com.example.gc_coffee.model.dto.OrderViewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderRepository {
// 기능만 정의 / 저장만 하는 부분이기때문에 반환타입 void

    // OrderRepository.java
    void insertOrder(@Param("dto") OrderDTO dto, @Param("totalPrice") int totalPrice);

    void insertOrderDetail(@Param("dto") OrderDTO dto,
                           @Param("quantity") int quantity,
                           @Param("coffeeId") long coffeeId);

    void deleteOrdersByEmail(@Param("email") String email);

    void updateOrderStatus(long orderId);

    // 모든 주문 조회
    List<OrderViewDTO> getAllOrdersWithDetails();
}