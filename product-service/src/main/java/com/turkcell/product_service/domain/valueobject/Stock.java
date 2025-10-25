package com.turkcell.product_service.domain.valueobject;

/**
 * Stock Value Object
 * Represents the stock quantity of a product
 * Immutable and self-validating
 */
public final class Stock {
    private final int value;

    private Stock(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.value = value;
    }

    public static Stock of(int value) {
        return new Stock(value);
    }

    public static Stock zero() {
        return new Stock(0);
    }

    public int getValue() {
        return value;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isAvailable() {
        return value > 0;
    }

    public Stock add(Stock other) {
        return new Stock(this.value + other.value);
    }

    public Stock subtract(Stock other) {
        int newValue = this.value - other.value;
        if (newValue < 0) {
            throw new IllegalArgumentException("Cannot subtract more stock than available");
        }
        return new Stock(newValue);
    }

    public Stock add(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot add negative amount");
        }
        return new Stock(this.value + amount);
    }

    public Stock subtract(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }
        int newValue = this.value - amount;
        if (newValue < 0) {
            throw new IllegalArgumentException("Cannot subtract more stock than available");
        }
        return new Stock(newValue);
    }

    public boolean hasEnough(Stock required) {
        return this.value >= required.value;
    }

    public boolean hasEnough(int required) {
        return this.value >= required;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Stock stock = (Stock) obj;
        return value == stock.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
