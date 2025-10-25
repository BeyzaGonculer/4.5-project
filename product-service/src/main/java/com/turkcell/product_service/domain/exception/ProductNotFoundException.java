package com.turkcell.product_service.domain.exception;

/**
 * Exception thrown when a Product is not found
 */
public class ProductNotFoundException extends ProductDomainException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
