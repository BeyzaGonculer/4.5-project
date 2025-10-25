package com.turkcell.product_service.application.usecase;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.port.ProductServicePort;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Use case for updating a product
 */
@Component
public class UpdateProductUseCase {

    private final ProductServicePort productServicePort;

    public UpdateProductUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    /**
     * Executes the update product use case
     * 
     * @param id      the product ID
     * @param request the update product request
     * @return the updated product response
     */
    public ProductResponse execute(UUID id, UpdateProductRequest request) {
        return productServicePort.updateProduct(id, request);
    }
}
