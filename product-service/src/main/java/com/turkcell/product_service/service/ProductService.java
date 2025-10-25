package com.turkcell.product_service.service;

import com.turkcell.product_service.domain.entity.Product;
import com.turkcell.product_service.domain.exception.ProductNotFoundException;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.*;
import com.turkcell.product_service.dto.CreateProductRequest;
import com.turkcell.product_service.dto.ProductResponse;
import com.turkcell.product_service.dto.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for Product operations
 * Contains business logic for CRUD operations
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product
     * 
     * @param request the create product request
     * @return the created product response
     */
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
        Product savedProduct = productRepository.save(product);

        // Convert to response
        return convertToResponse(savedProduct);
    }

    /**
     * Gets all products
     * 
     * @return list of all product responses
     */
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Gets a product by ID
     * 
     * @param id the product ID
     * @return the product response
     * @throws ProductNotFoundException if product not found
     */
    public ProductResponse getProductById(UUID id) {
        ProductId productId = ProductId.of(id);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return convertToResponse(product);
    }

    /**
     * Updates a product
     * 
     * @param id      the product ID
     * @param request the update product request
     * @return the updated product response
     * @throws ProductNotFoundException if product not found
     */
    public ProductResponse updateProduct(UUID id, UpdateProductRequest request) {
        ProductId productId = ProductId.of(id);
        Product product = productRepository.findById(productId)
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
        Product savedProduct = productRepository.save(product);

        // Convert to response
        return convertToResponse(savedProduct);
    }

    /**
     * Deletes a product
     * 
     * @param id the product ID
     * @throws ProductNotFoundException if product not found
     */
    public void deleteProduct(UUID id) {
        ProductId productId = ProductId.of(id);
        boolean deleted = productRepository.deleteById(productId);

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
