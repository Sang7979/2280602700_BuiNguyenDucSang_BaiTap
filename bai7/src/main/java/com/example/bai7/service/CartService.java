package com.example.bai7.service;

import com.example.bai7.model.CartItem;
import com.example.bai7.model.Product;
import com.example.bai7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class CartService {

    private final Map<Integer, CartItem> cart = new LinkedHashMap<>();

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getItems() {
        return new ArrayList<>(cart.values());
    }

    public void addToCart(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return;

        if (cart.containsKey(productId)) {
            CartItem item = cart.get(productId);
            item.setQuantity(item.getQuantity() + 1);
        } else {
            CartItem item = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getImage(),
                    product.getPrice(),
                    1
            );
            cart.put(productId, item);
        }
    }

    public void updateQuantity(Integer productId, Integer quantity) {
        if (!cart.containsKey(productId)) return;

        if (quantity == null || quantity <= 0) {
            cart.remove(productId);
        } else {
            cart.get(productId).setQuantity(quantity);
        }
    }

    public void remove(Integer productId) {
        cart.remove(productId);
    }

    public void clear() {
        cart.clear();
    }

    public Double getTotal() {
        return cart.values().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }
}