package com.turkcell.product_service.infrastructure.repository;

import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.ProductId;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of ProductRepository
 * Uses ConcurrentHashMap for thread-safe operations
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {
    
    private final Map<UUID, Product> products = new ConcurrentHashMap<>();
    
    @Override
    public Product save(Product product) {
        products.put(product.getId().getValue(), product);
        return product;
    }
    
    @Override
    public Optional<Product> findById(ProductId productId) {
        return Optional.ofNullable(products.get(productId.getValue()));
    }
    
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
    
    @Override
    public boolean deleteById(ProductId productId) {
        return products.remove(productId.getValue()) != null;
    }
    
    @Override
    public boolean existsById(ProductId productId) {
        return products.containsKey(productId.getValue());
    }
    
    @Override
    public long count() {
        return products.size();
    }
}
