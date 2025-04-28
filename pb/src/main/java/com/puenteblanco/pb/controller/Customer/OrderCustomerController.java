package com.puenteblanco.pb.controller.Customer;

import com.puenteblanco.pb.dto.request.OrderRequest;
import com.puenteblanco.pb.dto.response.OrderResponse;
import com.puenteblanco.pb.services.customer.OrderCustomerService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/orders")
public class OrderCustomerController {

    private final OrderCustomerService orderService;

    public OrderCustomerController(OrderCustomerService orderService) {
        this.orderService = orderService;
    }

    // Simulación: ID fijo (en producción usar autenticación)
    private final Long mockUserId = 1L;

    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequest request) {
        orderService.createOrder(mockUserId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrderHistory() {
        List<OrderResponse> orders = orderService.getOrdersByUser(mockUserId);
        return ResponseEntity.ok(orders);
    }
}
