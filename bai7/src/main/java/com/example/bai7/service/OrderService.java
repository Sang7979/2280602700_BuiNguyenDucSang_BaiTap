package com.example.bai7.service;

import com.example.bai7.model.*;
import com.example.bai7.repository.OrderDetailRepository;
import com.example.bai7.repository.OrderRepository;
import com.example.bai7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void checkout(Account account, CartService cartService) {
        if (account == null || cartService.isEmpty()) {
            return;
        }

        Order order = new Order();
        order.setAccount(account);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("NEW");
        order.setTotal(cartService.getTotal());

        Order savedOrder = orderRepository.save(order);

        for (CartItem item : cartService.getItems()) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product == null) continue;

            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(product);
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            detail.setSubtotal(item.getSubtotal());

            orderDetailRepository.save(detail);
        }

        cartService.clear();
    }
}