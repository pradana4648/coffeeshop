package com.specialteam.coffeshop.product.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ProductResponseDto<T> {
    private Boolean error;
    private Instant timestamp;
    private T results;

    public static <T> ResponseEntity<ProductResponseDto<T>> response(Boolean error, T results,
            HttpStatus status) {
        ProductResponseDto<T> response = new ProductResponseDto<>();
        response.setError(error);
        response.setTimestamp(Instant.now());
        response.setResults(results);
        return new ResponseEntity<ProductResponseDto<T>>(response, status);
    }
}
