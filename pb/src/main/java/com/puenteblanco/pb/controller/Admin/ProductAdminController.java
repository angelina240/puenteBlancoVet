package com.puenteblanco.pb.controller.Admin;
import com.puenteblanco.pb.dto.request.ProductRequest;
import com.puenteblanco.pb.dto.response.ProductResponse;
import com.puenteblanco.pb.services.admin.ProductAdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class ProductAdminController {

    private final ProductAdminService productService;

    public ProductAdminController(ProductAdminService productService) {
        this.productService = productService;
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Validated @RequestBody ProductRequest request) {
        ProductResponse response = productService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    // Actualizar producto por ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Validated @RequestBody ProductRequest request) {
        ProductResponse updated = productService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // Eliminar producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}