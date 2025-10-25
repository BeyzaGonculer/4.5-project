package com.turkcell.product_service.domain.valueobject;

/**
 * ProductName Value Object
 * Represents the name of a product
 * Immutable and self-validating
 */
public final class ProductName {
    private final String value;

    private ProductName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("ProductName cannot be null or empty");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("ProductName cannot exceed 255 characters");
        }
        this.value = value.trim();
    }

    public static ProductName of(String value) {
        return new ProductName(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductName that = (ProductName) obj;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
