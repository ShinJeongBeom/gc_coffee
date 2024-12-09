package com.example.gc_coffee.controller;

import com.example.gc_coffee.model.dto.UserDTO;
import com.example.gc_coffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 이메일로 사용자 정보 검색
    @GetMapping("/find")
    public String findUserByEmail(@RequestParam String email, Model model) {
        List<UserDTO> userList = userService.getUserByEmail(email);
        model.addAttribute("userList", userList);
        return "user_list"; // templates/user_list.html 렌더링
    }
}