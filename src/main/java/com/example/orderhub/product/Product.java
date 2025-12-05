package com.example.orderhub.product;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    protected Product() { }

    public Product(String name, BigDecimal price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // getter/setter 최소한만
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void changeStock(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void decreaseStock(int quantity) {
        int rest = this.stockQuantity - quantity;
        if (rest < 0) {
            throw new IllegalArgumentException("재고 부족");
        }
        this.stockQuantity = rest;
    }

    public void increaseStock(int quantity) {
        this.stockQuantity += quantity;
    }
}