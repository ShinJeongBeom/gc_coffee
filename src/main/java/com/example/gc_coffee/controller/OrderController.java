package com.example.gc_coffee.controller;

import com.example.gc_coffee.model.dto.CartItem;
import com.example.gc_coffee.model.dto.CoffeeDTO;
import com.example.gc_coffee.model.dto.OrderDTO;
import com.example.gc_coffee.model.dto.OrderViewDTO;
import com.example.gc_coffee.service.CoffeeService;
import com.example.gc_coffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CoffeeService coffeeService;

    @PostMapping("/add")
    public String addOrder(@RequestParam(name = "quantity") int quantity,
                           @RequestParam(name = "coffeeId") long coffeeId,
                           HttpSession session) {

        CoffeeDTO coffee = coffeeService.getCoffeeById(coffeeId);
        if (coffee == null) {
            return "redirect:/coffee/list";
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getCoffeeId() == coffeeId) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem newItem = new CartItem(coffee.getCoffeeId(), coffee.getCoffeeName(), coffee.getCoffeePrice(), quantity);
            cart.add(newItem);
        }

        session.setAttribute("cart", cart);

        return "redirect:/coffee/list";
    }

    @PostMapping("/delete")
    public String deleteOldOrders(@RequestParam String email, Model model) {
        orderService.deleteOldOrders(email);
        model.addAttribute("message", "주문이 성공적으로 삭제되었습니다.");
        return "redirect:/coffee/list";
    }

    @PostMapping("/update")
    public String updateStatus(@RequestParam long orderId, Model model) {
        orderService.updateOrder(orderId);
        model.addAttribute("message", "주문 상태가 업데이트되었습니다.");
        return "redirect:/coffee/list";
    }

    // 장바구니 아이템 개별 삭제를 위한 매핑
    @PostMapping("/removeItem")
    public String removeItem(@RequestParam long coffeeId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(item -> item.getCoffeeId() == coffeeId);
            session.setAttribute("cart", cart);
        }
        return "redirect:/coffee/list";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String email,
                           @RequestParam String address,
                           @RequestParam String zipcode,
                           HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "장바구니가 비어있습니다.");
            return "redirect:/coffee/list";
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setEmail(email);
        orderDTO.setAddress(address);
        orderDTO.setZipCode(zipcode);
        orderDTO.setStatus(false);

        for (CartItem item : cart) {
            orderService.insertUserOrder(orderDTO, item.getQuantity(), item.getCoffeeId());
        }

        session.removeAttribute("cart");

        return "redirect:/coffee/list";
    }

    @GetMapping("/listAll")
    public String listAllOrders(Model model) {
        List<OrderViewDTO> allOrders = orderService.getAllOrdersWithDetails();
        model.addAttribute("allOrders", allOrders);
        return "order_list"; // /WEB-INF/views/order_list.jsp로 forward
    }
}