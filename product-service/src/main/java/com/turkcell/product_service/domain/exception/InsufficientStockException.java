package com.turkcell.product_service.domain.exception;

/**
 * Exception thrown when there is insufficient stock for a product operation
 */
public class InsufficientStockException extends ProductDomainException {

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
