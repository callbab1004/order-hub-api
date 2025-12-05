package com.example.orderhub.order.dto;

import com.example.orderhub.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id,
                         String orderNumber,
                         OrderStatus status,
                         BigDecimal totalPrice,
                         LocalDateTime createdAt,
                         List<OrderItemResponse> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public static class OrderItemResponse {
        private Long productId;
        private String productName;
        private int quantity;
        private BigDecimal orderPrice;
        private BigDecimal lineAmount;

        public OrderItemResponse(Long productId,
                                 String productName,
                                 int quantity,
                                 BigDecimal orderPrice,
                                 BigDecimal lineAmount) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.orderPrice = orderPrice;
            this.lineAmount = lineAmount;
        }

        public Long getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getOrderPrice() {
            return orderPrice;
        }

        public BigDecimal getLineAmount() {
            return lineAmount;
        }
    }
}