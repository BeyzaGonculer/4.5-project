package com.turkcell.product_service.domain.service;

import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.domain.valueobject.Stock;

/**
 * Domain Service interface for Product operations
 * Contains business logic that doesn't naturally belong to the Product entity
 */
public interface ProductDomainService {

    /**
     * Validates if a product can be created with the given parameters
     */
    boolean canCreateProduct(String name, String description, double price, String currency, int stock);

    /**
     * Validates if stock can be reduced from a product
     */
    boolean canReduceStock(Product product, Stock amount);

    /**
     * Checks if a product exists by ID
     */
    boolean productExists(ProductId productId);

    /**
     * Calculates total value of products in stock
     */
    double calculateTotalValue(Product product);
}
