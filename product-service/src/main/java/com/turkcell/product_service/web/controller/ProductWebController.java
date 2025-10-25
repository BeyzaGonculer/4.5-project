package com.turkcell.product_service.web.controller;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.usecase.*;
import com.turkcell.product_service.web.dto.CreateProductWebRequest;
import com.turkcell.product_service.web.dto.ProductListWebResponse;
import com.turkcell.product_service.web.dto.ProductWebResponse;
import com.turkcell.product_service.web.dto.UpdateProductWebRequest;
import com.turkcell.product_service.web.mapper.ProductWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Web Controller for Product CRUD operations
 * Handles HTTP requests and responses for the web layer
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductWebController {

    private final CreateProductUseCase createProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductWebMapper productWebMapper;

    public ProductWebController(CreateProductUseCase createProductUseCase,
            GetAllProductsUseCase getAllProductsUseCase,
            GetProductByIdUseCase getProductByIdUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase,
            ProductWebMapper productWebMapper) {
        this.createProductUseCase = createProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.productWebMapper = productWebMapper;
    }

    /**
     * GET /api/v1/products - Get all products
     * 
     * @return list of all products with statistics
     */
    @GetMapping
    public ResponseEntity<ProductListWebResponse> getAllProducts() {
        ProductListResponse applicationResponse = getAllProductsUseCase.execute();
        ProductListWebResponse webResponse = productWebMapper.toWebResponse(applicationResponse);
        return ResponseEntity.ok(webResponse);
    }

    /**
     * GET /api/v1/products/{id} - Get product by ID
     * 
     * @param id the product ID
     * @return the product
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductWebResponse> getProductById(@PathVariable UUID id) {
        ProductResponse applicationResponse = getProductByIdUseCase.execute(id);
        ProductWebResponse webResponse = productWebMapper.toWebResponse(applicationResponse);
        return ResponseEntity.ok(webResponse);
    }

    /**
     * POST /api/v1/products - Create a new product
     * 
     * @param webRequest the create product web request
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<ProductWebResponse> createProduct(@Valid @RequestBody CreateProductWebRequest webRequest) {
        CreateProductRequest applicationRequest = productWebMapper.toApplicationRequest(webRequest);
        ProductResponse applicationResponse = createProductUseCase.execute(applicationRequest);
        ProductWebResponse webResponse = productWebMapper.toWebResponse(applicationResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

    /**
     * PUT /api/v1/products/{id} - Update a product
     * 
     * @param id         the product ID
     * @param webRequest the update product web request
     * @return the updated product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductWebResponse> updateProduct(@PathVariable UUID id,
            @Valid @RequestBody UpdateProductWebRequest webRequest) {
        UpdateProductRequest applicationRequest = productWebMapper.toApplicationRequest(webRequest);
        ProductResponse applicationResponse = updateProductUseCase.execute(id, applicationRequest);
        ProductWebResponse webResponse = productWebMapper.toWebResponse(applicationResponse);
        return ResponseEntity.ok(webResponse);
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
