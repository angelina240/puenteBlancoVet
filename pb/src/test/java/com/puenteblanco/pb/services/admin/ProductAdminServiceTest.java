package com.puenteblanco.pb.services.admin;


import com.puenteblanco.pb.dto.request.ProductRequest;
import com.puenteblanco.pb.dto.response.ProductResponse;
import com.puenteblanco.pb.entity.Category;
import com.puenteblanco.pb.entity.Product;
import com.puenteblanco.pb.exception.ResourceNotFoundException;
import com.puenteblanco.pb.repository.CategoryRepository;
import com.puenteblanco.pb.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductAdminServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductAdminService productService;

    @Test
    public void testCreateProduct_success() {
        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Collar Antipulgas");
        request.setDescription("Collar de seguridad para gatos");
        request.setPrice(19.99);
        request.setStock(30);
        request.setImage("collar.jpg");
        request.setCategoryId(1L);

        Category mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Accesorios");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        // Act
        ProductResponse response = productService.create(request);

        // Assert
        assertNotNull(response);
        assertEquals("Collar Antipulgas", response.getName());
        assertEquals("collar-antipulgas", response.getSlug());
        assertEquals("Accesorios", response.getCategoryName());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testCreateProduct_categoryNotFound() {
        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Collar");
        request.setCategoryId(99L); // ID no existente

        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> productService.create(request));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testGetAllProducts_returnsList() {
        // Arrange
        Category cat = new Category();
        cat.setName("Comida");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Croquetas");
        product1.setSlug("croquetas");
        product1.setDescription("Croquetas para perro");
        product1.setPrice(15.5);
        product1.setStock(50);
        product1.setImage("croquetas.jpg");
        product1.setCategory(cat);

        when(productRepository.findAll()).thenReturn(List.of(product1));

        // Act
        List<ProductResponse> products = productService.getAll();

        // Assert
        assertEquals(1, products.size());
        assertEquals("Croquetas", products.get(0).getName());
    }
}