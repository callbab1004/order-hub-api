package com.example.orderhub.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;
}