package com.example.bai7.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private Double total;

    private String status;

    public Integer getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}