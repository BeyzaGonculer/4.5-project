package com.turkcell.product_service.domain.exception;

/**
 * Base exception for all domain-related errors in the Product domain
 */
public class ProductDomainException extends RuntimeException {

    public ProductDomainException(String message) {
        super(message);
    }

    public ProductDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
