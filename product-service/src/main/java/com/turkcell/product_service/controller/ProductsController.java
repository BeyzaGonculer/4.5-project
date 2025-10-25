package com.turkcell.product_service.controller;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.usecase.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST Controller for Product CRUD operations
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final CreateProductUseCase createProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductsController(CreateProductUseCase createProductUseCase,
            GetAllProductsUseCase getAllProductsUseCase,
            GetProductByIdUseCase getProductByIdUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    /**
     * GET /api/v1/products - Get all products
     * 
     * @return list of all products
     */
    @GetMapping
    public ResponseEntity<ProductListResponse> getAllProducts() {
        ProductListResponse response = getAllProductsUseCase.execute();
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/products/{id} - Get product by ID
     * 
     * @param id the product ID
     * @return the product
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        ProductResponse product = getProductByIdUseCase.execute(id);
        return ResponseEntity.ok(product);
    }

    /**
     * POST /api/v1/products - Create a new product
     * 
     * @param request the create product request
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse product = createProductUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * PUT /api/v1/products/{id} - Update a product
     * 
     * @param id      the product ID
     * @param request the update product request
     * @return the updated product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        ProductResponse product = updateProductUseCase.execute(id, request);
        return ResponseEntity.ok(product);
    }

    /**
     * DELETE /api/v1/products/{id} - Delete a product
     * 
     * @param id the product ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}