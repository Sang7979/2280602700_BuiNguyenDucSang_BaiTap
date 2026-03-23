package com.example.bai7.controller;

import com.example.bai7.model.Account;
import com.example.bai7.repository.AccountRepository;
import com.example.bai7.service.CartService;
import com.example.bai7.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/checkout")
    public String checkout(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        Account account = accountRepository.findByUsername(username).orElse(null);

        orderService.checkout(account, cartService);

        return "redirect:/products";
    }
}