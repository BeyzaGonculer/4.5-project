package com.turkcell.product_service.domain.valueobject;

/**
 * Description Value Object
 * Represents the description of a product
 * Immutable and self-validating
 */
public final class Description {
    private final String value;

    private Description(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (value.length() > 1000) {
            throw new IllegalArgumentException("Description cannot exceed 1000 characters");
        }
        this.value = value.trim();
    }

    public static Description of(String value) {
        return new Description(value);
    }

    public static Description empty() {
        return new Description("");
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Description that = (Description) obj;
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
