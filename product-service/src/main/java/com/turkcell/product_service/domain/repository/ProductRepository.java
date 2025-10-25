package com.turkcell.product_service.domain.repository;

import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.valueobject.ProductId;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Product entity
 * Defines the contract for product data access operations
 */
public interface ProductRepository {
    
    /**
     * Saves a product to the repository
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);
    
    /**
     * Finds a product by its ID
     * @param productId the product ID
     * @return Optional containing the product if found, empty otherwise
     */
    Optional<Product> findById(ProductId productId);
    
    /**
     * Finds all products
     * @return list of all products
     */
    List<Product> findAll();
    
    /**
     * Deletes a product by its ID
     * @param productId the product ID
     * @return true if the product was deleted, false if not found
     */
    boolean deleteById(ProductId productId);
    
    /**
     * Checks if a product exists by its ID
     * @param productId the product ID
     * @return true if the product exists, false otherwise
     */
    boolean existsById(ProductId productId);
    
    /**
     * Counts the total number of products
     * @return the total number of products
     */
    long count();
}
