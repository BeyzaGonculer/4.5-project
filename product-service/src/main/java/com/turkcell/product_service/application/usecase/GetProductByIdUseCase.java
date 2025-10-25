package com.turkcell.product_service.application.usecase;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.port.ProductServicePort;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Use case for getting a product by ID
 */
@Component
public class GetProductByIdUseCase {

    private final ProductServicePort productServicePort;

    public GetProductByIdUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    /**
     * Executes the get product by ID use case
     * 
     * @param id the product ID
     * @return the product response
     */
    public ProductResponse execute(UUID id) {
        return productServicePort.getProductById(id);
    }
}
