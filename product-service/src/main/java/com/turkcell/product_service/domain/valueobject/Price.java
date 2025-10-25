package com.turkcell.product_service.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Price Value Object
 * Represents the price of a product
 * Immutable and self-validating
 */
public final class Price {
    private final BigDecimal value;

    private Price(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        // Round to 2 decimal places for currency precision
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public static Price of(double value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price of(long value) {
        return new Price(BigDecimal.valueOf(value));
    }

    public static Price zero() {
        return new Price(BigDecimal.ZERO);
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public Price add(Price other) {
        return new Price(this.value.add(other.value));
    }

    public Price subtract(Price other) {
        return new Price(this.value.subtract(other.value));
    }

    public Price multiply(BigDecimal factor) {
        return new Price(this.value.multiply(factor));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Price price = (Price) obj;
        return value.equals(price.value);
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
