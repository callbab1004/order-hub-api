package com.example.orderhub.order;

import com.example.orderhub.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal orderPrice; // 주문 시점의 가격 스냅샷

    @Column(nullable = false)
    private int quantity;

    protected OrderItem() { }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.orderPrice = product.getPrice();
        this.quantity = quantity;
    }

    public BigDecimal getLineAmount() {
        return orderPrice.multiply(BigDecimal.valueOf(quantity));
    }

    void setOrder(Order order) {
        this.order = order;
    }

    // getters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}