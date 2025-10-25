package com.turkcell.product_service.application.service;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.port.ProductRepositoryPort;
import com.turkcell.product_service.application.port.ProductServicePort;
import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.exception.ProductNotFoundException;
import com.turkcell.product_service.domain.valueobject.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adapter implementation of ProductServicePort
 * Implements the application service interface and handles business logic
 */
@Service
public class ProductServiceAdapter implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductServiceAdapter(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        // Create value objects
        ProductName name = ProductName.of(request.getName());
        Description description = Description.of(request.getDescription());
        Price price = Price.of(request.getPrice());
        Currency currency = Currency.of(request.getCurrency());
        Stock stock = Stock.of(request.getStock());

        // Create product entity
        Product product = Product.create(name, description, price, currency, stock);

        // Save product
        Product savedProduct = productRepositoryPort.save(product);

        // Convert to response
        return convertToResponse(savedProduct);
    }

    @Override
    public ProductListResponse getAllProducts() {
        List<Product> products = productRepositoryPort.findAll();

        List<ProductResponse> productResponses = products.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        // Calculate statistics
        int totalCount = productResponses.size();
        int availableCount = (int) productResponses.stream()
                .filter(ProductResponse::isAvailable)
                .count();
        int outOfStockCount = totalCount - availableCount;

        return new ProductListResponse(productResponses, totalCount, availableCount, outOfStockCount);
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        ProductId productId = ProductId.of(id);
        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return convertToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(UUID id, UpdateProductRequest request) {
        ProductId productId = ProductId.of(id);
        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Create value objects
        ProductName name = ProductName.of(request.getName());
        Description description = Description.of(request.getDescription());
        Price price = Price.of(request.getPrice());
        Currency currency = Currency.of(request.getCurrency());
        Stock stock = Stock.of(request.getStock());

        // Update product
        product.updateInfo(name, description, price, currency);
        product.updateStock(stock);

        // Save updated product
        Product savedProduct = productRepositoryPort.save(product);

        // Convert to response
        return convertToResponse(savedProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        ProductId productId = ProductId.of(id);
        boolean deleted = productRepositoryPort.deleteById(productId);

        if (!deleted) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

    /**
     * Converts a Product entity to ProductResponse DTO
     * 
     * @param product the product entity
     * @return the product response
     */
    private ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId().getValue());
        response.setName(product.getName().getValue());
        response.setDescription(product.getDescription().getValue());
        response.setPrice(product.getPrice().getValue());
        response.setCurrency(product.getCurrency().getValue());
        response.setCurrencySymbol(product.getCurrency().getSymbol());
        response.setStock(product.getStock().getValue());
        response.setFormattedPrice(product.getFormattedPrice());
        response.setAvailable(product.isAvailable());
        response.setOutOfStock(product.isOutOfStock());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());

        return response;
    }
}
