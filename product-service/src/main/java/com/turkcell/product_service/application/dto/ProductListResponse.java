package com.turkcell.product_service.application.dto;

import java.util.List;

/**
 * Application DTO for product list response
 */
public class ProductListResponse {

    private List<ProductResponse> products;
    private int totalCount;
    private int availableCount;
    private int outOfStockCount;

    // Default constructor
    public ProductListResponse() {
    }

    // Constructor with all fields
    public ProductListResponse(List<ProductResponse> products, int totalCount, int availableCount,
            int outOfStockCount) {
        this.products = products;
        this.totalCount = totalCount;
        this.availableCount = availableCount;
        this.outOfStockCount = outOfStockCount;
    }

    // Getters and Setters
    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
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

    @Override
    public String toString() {
        return "ProductListResponse{" +
                "products=" + products +
                ", totalCount=" + totalCount +
                ", availableCount=" + availableCount +
                ", outOfStockCount=" + outOfStockCount +
                '}';
    }
}
