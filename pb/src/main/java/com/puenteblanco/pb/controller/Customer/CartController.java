package com.puenteblanco.pb.controller.Customer;

import com.puenteblanco.pb.dto.request.CartItemRequest;
import com.puenteblanco.pb.dto.response.CartItemResponse;
import com.puenteblanco.pb.services.customer.CartService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Simulamos userId = 1L como ejemplo (luego se reemplaza con autenticación)
    private final Long mockUserId = 1L;

    @PostMapping
    public ResponseEntity<Void> addToCart(@Valid @RequestBody CartItemRequest request) {
        cartService.addItem(mockUserId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCartItem(@Valid @RequestBody CartItemRequest request) {
        cartService.updateItem(mockUserId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId) {
        cartService.removeItem(mockUserId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItemResponse>> getCartItems() {
        List<CartItemResponse> cartItems = cartService.getCartItems(mockUserId);
        return ResponseEntity.ok(cartItems);
    }
}