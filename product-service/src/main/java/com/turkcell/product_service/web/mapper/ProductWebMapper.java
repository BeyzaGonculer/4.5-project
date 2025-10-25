package com.turkcell.product_service.web.mapper;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.web.dto.CreateProductWebRequest;
import com.turkcell.product_service.web.dto.ProductListWebResponse;
import com.turkcell.product_service.web.dto.ProductWebResponse;
import com.turkcell.product_service.web.dto.UpdateProductWebRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Web DTOs and Application DTOs
 * Handles the conversion between web layer and application layer
 */
@Component
public class ProductWebMapper {

    /**
     * Converts CreateProductWebRequest to CreateProductRequest
     * 
     * @param webRequest the web request DTO
     * @return the application request DTO
     */
    public CreateProductRequest toApplicationRequest(CreateProductWebRequest webRequest) {
        return new CreateProductRequest(
                webRequest.getName(),
                webRequest.getDescription(),
                webRequest.getPrice(),
                webRequest.getCurrency(),
                webRequest.getStock());
    }

    /**
     * Converts UpdateProductWebRequest to UpdateProductRequest
     * 
     * @param webRequest the web request DTO
     * @return the application request DTO
     */
    public UpdateProductRequest toApplicationRequest(UpdateProductWebRequest webRequest) {
        return new UpdateProductRequest(
                webRequest.getName(),
                webRequest.getDescription(),
                webRequest.getPrice(),
                webRequest.getCurrency(),
                webRequest.getStock());
    }

    /**
     * Converts ProductResponse to ProductWebResponse
     * 
     * @param applicationResponse the application response DTO
     * @return the web response DTO
     */
    public ProductWebResponse toWebResponse(ProductResponse applicationResponse) {
        return new ProductWebResponse(
                applicationResponse.getId(),
                applicationResponse.getName(),
                applicationResponse.getDescription(),
                applicationResponse.getPrice(),
                applicationResponse.getCurrency(),
                applicationResponse.getCurrencySymbol(),
                applicationResponse.getStock(),
                applicationResponse.getFormattedPrice(),
                applicationResponse.isAvailable(),
                applicationResponse.isOutOfStock(),
                applicationResponse.getCreatedAt(),
                applicationResponse.getUpdatedAt());
    }

    /**
     * Converts ProductListResponse to ProductListWebResponse
     * 
     * @param applicationResponse the application list response DTO
     * @return the web list response DTO
     */
    public ProductListWebResponse toWebResponse(ProductListResponse applicationResponse) {
        List<ProductWebResponse> webProducts = applicationResponse.getProducts().stream()
                .map(this::toWebResponse)
                .collect(Collectors.toList());

        String message = String.format("Found %d products (%d available, %d out of stock)",
                applicationResponse.getTotalCount(),
                applicationResponse.getAvailableCount(),
                applicationResponse.getOutOfStockCount());

        return new ProductListWebResponse(
                webProducts,
                applicationResponse.getTotalCount(),
                applicationResponse.getAvailableCount(),
                applicationResponse.getOutOfStockCount(),
                message);
    }
}
