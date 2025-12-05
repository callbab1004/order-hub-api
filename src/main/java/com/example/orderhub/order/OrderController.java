package com.example.orderhub.order;

import com.example.orderhub.order.dto.OrderCreateRequest;
import com.example.orderhub.order.dto.OrderResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getOne(@PathVariable Long id) {
        return orderService.getOrder(id);
    }
}