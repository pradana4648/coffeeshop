package com.specialteam.coffeshop.product.controller;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialteam.coffeshop.product.dto.ProductDto;
import com.specialteam.coffeshop.product.dto.ProductResponseDto;
import com.specialteam.coffeshop.product.service.ProductService;

@SpringBootTest
@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProducts() {
        ProductResponseDto<List<ProductDto>> responseDto = new ProductResponseDto<>();
        responseDto.setTimestamp(Instant.now());
        responseDto.setResults(service.getProducts());

        ObjectMapper mapper = new ObjectMapper();

    }
}
