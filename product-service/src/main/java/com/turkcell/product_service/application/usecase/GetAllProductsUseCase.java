package com.turkcell.product_service.application.usecase;

import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.port.ProductServicePort;
import org.springframework.stereotype.Component;

/**
 * Use case for getting all products
 */
@Component
public class GetAllProductsUseCase {

    private final ProductServicePort productServicePort;

    public GetAllProductsUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    /**
     * Executes the get all products use case
     * 
     * @return the product list response
     */
    public ProductListResponse execute() {
        return productServicePort.getAllProducts();
    }
}
