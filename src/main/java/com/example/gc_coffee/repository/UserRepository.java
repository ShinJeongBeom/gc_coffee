package com.example.gc_coffee.repository;
import com.example.gc_coffee.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {
    //기능만 정의
    List<UserDTO> findByEmail(@Param("email") String email);
}