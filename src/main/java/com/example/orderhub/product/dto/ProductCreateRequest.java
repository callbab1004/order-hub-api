package com.example.orderhub.product.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductCreateRequest {
    private String name;
    private BigDecimal price;
    private int stockQuantity;
}