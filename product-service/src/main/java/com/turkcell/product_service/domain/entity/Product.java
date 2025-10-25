package com.turkcell.product_service.domain.entity;

import com.turkcell.product_service.domain.exception.InsufficientStockException;
import com.turkcell.product_service.domain.valueobject.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Product Entity
 * Represents a product in the e-commerce system
 * Follows DDD principles with encapsulated business logic
 */
public class Product {

    private final ProductId id;
    private ProductName name;
    private Description description;
    private Price price;
    private Currency currency;
    private Stock stock;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Private constructor to enforce creation through factory methods
    private Product(ProductId id, ProductName name, Description description,
            Price price, Currency currency, Stock stock,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "Product ID cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.description = Objects.requireNonNull(description, "Product description cannot be null");
        this.price = Objects.requireNonNull(price, "Product price cannot be null");
        this.currency = Objects.requireNonNull(currency, "Product currency cannot be null");
        this.stock = Objects.requireNonNull(stock, "Product stock cannot be null");
        this.createdAt = Objects.requireNonNull(createdAt, "Created date cannot be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Updated date cannot be null");
    }

    /**
     * Factory method to create a new product
     */
    public static Product create(ProductName name, Description description,
            Price price, Currency currency, Stock stock) {
        LocalDateTime now = LocalDateTime.now();
        return new Product(
                ProductId.generate(),
                name,
                description,
                price,
                currency,
                stock,
                now,
                now);
    }

    /**
     * Factory method to reconstruct a product from persistence
     */
    public static Product reconstruct(ProductId id, ProductName name, Description description,
            Price price, Currency currency, Stock stock,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Product(id, name, description, price, currency, stock, createdAt, updatedAt);
    }

    /**
     * Updates product information
     */
    public void updateInfo(ProductName name, Description description, Price price, Currency currency) {
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.description = Objects.requireNonNull(description, "Product description cannot be null");
        this.price = Objects.requireNonNull(price, "Product price cannot be null");
        this.currency = Objects.requireNonNull(currency, "Product currency cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Updates stock quantity
     */
    public void updateStock(Stock newStock) {
        this.stock = Objects.requireNonNull(newStock, "Stock cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Adds stock to the product
     */
    public void addStock(Stock amount) {
        this.stock = this.stock.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Reduces stock from the product
     */
    public void reduceStock(Stock amount) {
        if (!this.stock.hasEnough(amount)) {
            throw new InsufficientStockException(
                    String.format("Insufficient stock. Available: %d, Requested: %d",
                            this.stock.getValue(), amount.getValue()));
        }
        this.stock = this.stock.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Checks if the product is available (has stock)
     */
    public boolean isAvailable() {
        return stock.isAvailable();
    }

    /**
     * Checks if the product is out of stock
     */
    public boolean isOutOfStock() {
        return stock.isZero();
    }

    /**
     * Gets the formatted price with currency
     */
    public String getFormattedPrice() {
        return currency.getSymbol() + price.getValue().toString();
    }

    // Getters
    public ProductId getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product product = (Product) obj;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Product{id=%s, name=%s, price=%s, currency=%s, stock=%s}",
                id, name, price, currency, stock);
    }
}
