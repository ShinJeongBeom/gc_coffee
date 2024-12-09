package com.example.gc_coffee.service;

import com.example.gc_coffee.model.dto.UserDTO;
import com.example.gc_coffee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}