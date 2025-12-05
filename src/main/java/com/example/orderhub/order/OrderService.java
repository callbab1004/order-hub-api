package com.example.orderhub.order;

import com.example.orderhub.order.dto.OrderCreateRequest;
import com.example.orderhub.order.dto.OrderResponse;
import com.example.orderhub.product.Product;
import com.example.orderhub.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderCreateRequest request) {
        // 주문번호 간단하게 UUID 일부 사용
        String orderNumber = UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order(orderNumber);

        // 각 아이템 처리
        request.getItems().forEach(itemReq -> {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("상품 없음: " + itemReq.getProductId()));

            // 재고 차감 (부족하면 예외)
            product.decreaseStock(itemReq.getQuantity());

            OrderItem orderItem = new OrderItem(product, itemReq.getQuantity());
            order.addItem(orderItem);
        });

        Order saved = orderRepository.save(order);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문 없음: " + id));
        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        List<OrderResponse.OrderItemResponse> itemResponses = order.getItems().stream()
                .map(oi -> new OrderResponse.OrderItemResponse(
                        oi.getProduct().getId(),
                        oi.getProduct().getName(),
                        oi.getQuantity(),
                        oi.getOrderPrice(),
                        oi.getLineAmount()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                itemResponses
        );
    }
}