package com.turkcell.product_service.application.usecase;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.port.ProductServicePort;
import org.springframework.stereotype.Component;

/**
 * Use case for creating a new product
 */
@Component
public class CreateProductUseCase {

    private final ProductServicePort productServicePort;

    public CreateProductUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    /**
     * Executes the create product use case
     * 
     * @param request the create product request
     * @return the created product response
     */
    public ProductResponse execute(CreateProductRequest request) {
        return productServicePort.createProduct(request);
    }
}
