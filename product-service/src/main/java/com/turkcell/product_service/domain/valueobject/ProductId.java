package com.turkcell.product_service.domain.valueobject;

import java.util.UUID;

/**
 * ProductId Value Object
 * Represents a unique identifier for a Product entity
 * Immutable and self-validating
 */
public final class ProductId {
    private final UUID value;

    private ProductId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        this.value = value;
    }

    public static ProductId of(UUID value) {
        return new ProductId(value);
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductId productId = (ProductId) obj;
        return value.equals(productId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
