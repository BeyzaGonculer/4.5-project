package com.turkcell.product_service.application.usecase;

import com.turkcell.product_service.application.port.ProductServicePort;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Use case for deleting a product
 */
@Component
public class DeleteProductUseCase {

    private final ProductServicePort productServicePort;

    public DeleteProductUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    /**
     * Executes the delete product use case
     * 
     * @param id the product ID
     */
    public void execute(UUID id) {
        productServicePort.deleteProduct(id);
    }
}
