package com.specialteam.coffeshop.product.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ProductResponseDto<T> {
    private Instant timestamp;
    private T results;

    public static <T> ResponseEntity<ProductResponseDto<T>> response(T results,
            HttpStatus status) {
        ProductResponseDto<T> response = new ProductResponseDto<>();
        response.setTimestamp(Instant.now());
        response.setResults(results);
        return new ResponseEntity<ProductResponseDto<T>>(response, status);
    }
}
