package com.example.gc_coffee.controller;

import com.example.gc_coffee.model.dto.CoffeeDTO;
import com.example.gc_coffee.model.dto.CartItem;
import com.example.gc_coffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/list")
    public String listCoffee(HttpSession session, Model model) {
        List<CoffeeDTO> coffeeList = coffeeService.getAllCoffeeList();
        model.addAttribute("coffeeList", coffeeList);

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cart", cart);

        int totalPrice = cart.stream().mapToInt(item -> item.getPrice() * item.getQuantity()).sum();
        model.addAttribute("totalPrice", totalPrice);

        return "coffee/list";
    }

    @PostMapping("/removeItem")
    public String removeItem(@RequestParam long coffeeId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                CartItem item = cart.get(i);
                if (item.getCoffeeId() == coffeeId) {
                    // 개수를 하나 감소
                    int newQuantity = item.getQuantity() - 1;
                    if (newQuantity <= 0) {
                        // 0개 이하가 되면 해당 상품 삭제
                        cart.remove(i);
                    } else {
                        // 새 개수 반영
                        item.setQuantity(newQuantity);
                    }
                    break; // 대상 상품 처리 후 loop 종료
                }
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/coffee/list";
    }
}
