package com.example.orderhub.order.dto;

import java.util.List;

public class OrderCreateRequest {
    private List<OrderItemRequest> items;

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public static class OrderItemRequest {
        private Long productId;
        private int quantity;

        public Long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}