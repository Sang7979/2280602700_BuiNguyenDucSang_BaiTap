package com.example.bai7.controller;

import com.example.bai7.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart/list";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id) {
        cartService.addToCart(id);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Integer productId,
                             @RequestParam Integer quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Integer id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}