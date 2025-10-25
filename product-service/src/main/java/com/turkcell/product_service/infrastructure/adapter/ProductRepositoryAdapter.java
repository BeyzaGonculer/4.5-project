package com.turkcell.product_service.infrastructure.adapter;

import com.turkcell.product_service.application.port.ProductRepositoryPort;
import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.infrastructure.repository.InMemoryProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adapter implementation of ProductRepositoryPort
 * Bridges the application layer with the infrastructure layer
 */
@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final InMemoryProductRepository inMemoryProductRepository;

    public ProductRepositoryAdapter(InMemoryProductRepository inMemoryProductRepository) {
        this.inMemoryProductRepository = inMemoryProductRepository;
    }

    @Override
    public Product save(Product product) {
        return inMemoryProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return inMemoryProductRepository.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return inMemoryProductRepository.findAll();
    }

    @Override
    public boolean deleteById(ProductId productId) {
        return inMemoryProductRepository.deleteById(productId);
    }

    @Override
    public boolean existsById(ProductId productId) {
        return inMemoryProductRepository.existsById(productId);
    }

    @Override
    public long count() {
        return inMemoryProductRepository.count();
    }
}
