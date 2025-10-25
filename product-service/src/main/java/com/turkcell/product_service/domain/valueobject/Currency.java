package com.turkcell.product_service.domain.valueobject;

/**
 * Currency Value Object
 * Represents the currency of a product price
 * Immutable and self-validating
 */
public final class Currency {
    private final String value;

    private Currency(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        if (value.length() != 3) {
            throw new IllegalArgumentException("Currency must be a 3-letter ISO code");
        }
        this.value = value.trim().toUpperCase();
    }

    public static Currency of(String value) {
        return new Currency(value);
    }

    public static Currency USD() {
        return new Currency("USD");
    }

    public static Currency EUR() {
        return new Currency("EUR");
    }

    public static Currency TRY() {
        return new Currency("TRY");
    }

    public String getValue() {
        return value;
    }

    public String getSymbol() {
        return switch (value) {
            case "USD" -> "$";
            case "EUR" -> "€";
            case "TRY" -> "₺";
            default -> value;
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Currency currency = (Currency) obj;
        return value.equals(currency.value);
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
