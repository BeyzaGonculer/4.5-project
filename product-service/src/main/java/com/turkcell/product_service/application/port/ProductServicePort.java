package com.turkcell.product_service.application.port;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;

import java.util.UUID;

/**
 * Port interface for Product Service operations
 * Defines the contract for product business operations
 */
public interface ProductServicePort {

    /**
     * Creates a new product
     * 
     * @param request the create product request
     * @return the created product response
     */
    ProductResponse createProduct(CreateProductRequest request);

    /**
     * Gets all products
     * 
     * @return the product list response
     */
    ProductListResponse getAllProducts();

    /**
     * Gets a product by ID
     * 
     * @param id the product ID
     * @return the product response
     */
    ProductResponse getProductById(UUID id);

    /**
     * Updates a product
     * 
     * @param id      the product ID
     * @param request the update product request
     * @return the updated product response
     */
    ProductResponse updateProduct(UUID id, UpdateProductRequest request);

    /**
     * Deletes a product
     * 
     * @param id the product ID
     */
    void deleteProduct(UUID id);
}
