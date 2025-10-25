package com.turkcell.product_service.web.dto;

import java.util.List;

/**
 * Web DTO for product list response
 * Contains additional metadata for web layer
 */
public class ProductListWebResponse {

    private List<ProductWebResponse> products;
    private int totalCount;
    private int availableCount;
    private int outOfStockCount;
    private String message;

    // Default constructor
    public ProductListWebResponse() {
    }

    // Constructor with all fields
    public ProductListWebResponse(List<ProductWebResponse> products, int totalCount,
            int availableCount, int outOfStockCount, String message) {
        this.products = products;
        this.totalCount = totalCount;
        this.availableCount = availableCount;
        this.outOfStockCount = outOfStockCount;
        this.message = message;
    }

    // Getters and Setters
    public List<ProductWebResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWebResponse> products) {
        this.products = products;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getOutOfStockCount() {
        return outOfStockCount;
    }

    public void setOutOfStockCount(int outOfStockCount) {
        this.outOfStockCount = outOfStockCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductListWebResponse{" +
                "products=" + products +
                ", totalCount=" + totalCount +
                ", availableCount=" + availableCount +
                ", outOfStockCount=" + outOfStockCount +
                ", message='" + message + '\'' +
                '}';
    }
}
