package com.puenteblanco.pb.controller.Customer;

import com.puenteblanco.pb.dto.response.ProductSummaryResponse;
import com.puenteblanco.pb.services.customer.ProductCustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.ui.Model;  // Asegúrate de importar esto

@RestController
@RequestMapping("/api/products")
public class ProductCustomerController {

    private final ProductCustomerService productService;

    public ProductCustomerController(ProductCustomerService productService) {
        this.productService = productService;
    }

    // Obtener todos los productos disponibles
    @GetMapping
    public ResponseEntity<List<ProductSummaryResponse>> getAllProducts() {
        List<ProductSummaryResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Obtener un producto por su slug (detalle)
    @GetMapping("/{slug}")
    public ResponseEntity<ProductSummaryResponse> getProductBySlug(@PathVariable String slug) {
        ProductSummaryResponse product = productService.getBySlug(slug);
        return ResponseEntity.ok(product);
    }
    
    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        List<ProductSummaryResponse> productos = productService.getAllProducts();
        model.addAttribute("products", productos);
        return "products"; // Aquí sí renderiza la plantilla products.html
    }

}
