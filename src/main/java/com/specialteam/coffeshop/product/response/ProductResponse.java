package com.specialteam.coffeshop.product.response;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ProductResponse<T> {
    private Boolean error;
    private Instant timestamp;
    private T results;

    public static <T> ResponseEntity<ProductResponse<T>> response(Boolean error, T results,
            HttpStatus status) {
        ProductResponse<T> response = new ProductResponse<>();
        response.setError(error);
        response.setTimestamp(Instant.now());
        response.setResults(results);
        return new ResponseEntity<ProductResponse<T>>(response, status);
    }
}
