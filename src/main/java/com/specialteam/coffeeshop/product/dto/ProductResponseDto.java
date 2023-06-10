package com.specialteam.coffeeshop.product.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ProductResponseDto<T> {
    private String dateTime;
    private T results;

    public static <T> ResponseEntity<ProductResponseDto<T>> response(T results, HttpStatus status) {
        ProductResponseDto<T> response = new ProductResponseDto<>();
        response.setDateTime(new SimpleDateFormat("dd MMMM yyyy HH:mm:ss").format(new Date()));
        response.setResults(results);
        return new ResponseEntity<ProductResponseDto<T>>(response, status);
    }
}
