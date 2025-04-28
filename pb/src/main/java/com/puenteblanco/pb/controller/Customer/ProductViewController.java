package com.puenteblanco.pb.controller.Customer;

import com.puenteblanco.pb.dto.response.ProductSummaryResponse;
import com.puenteblanco.pb.services.customer.ProductCustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vista/productos")
public class ProductViewController {

    private final ProductCustomerService productService;

    public ProductViewController(ProductCustomerService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String mostrarProductos(Model model) {
        List<ProductSummaryResponse> productos = productService.getAllProducts();
        model.addAttribute("products", productos);
        return "products"; // busca src/main/resources/templates/products.html
    }
}
