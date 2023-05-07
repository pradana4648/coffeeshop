package com.specialteam.coffeshop.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialteam.coffeshop.model.Product;
import com.specialteam.coffeshop.model.ProductImage;
import com.specialteam.coffeshop.repository.ProductRepository;
import com.specialteam.coffeshop.request.ProductRequest;
import com.specialteam.coffeshop.response.ProductResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ResponseEntity<Map<String, Object>> addProduct(String request, MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> body;

        try {
            ProductRequest productRequest = mapper.readValue(request, ProductRequest.class);
            Product entity = new Product();
            entity.setId(UUID.randomUUID().toString());
            entity.setName(productRequest.getName());
            entity.setDescription(productRequest.getDescription());
            entity.setPrice(productRequest.getPrice());
            entity.setQuantity(1);
            entity.setImage(imageToBytes(file));
            repository.save(entity);
            body = new HashMap<>();
            body.put("status", 1);
            body.put("error", null);
            body.put("data", null);
            body.put("message", null);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private ProductImage imageToBytes(MultipartFile file) {

        ProductImage image = new ProductImage();
        try {
            image.setFilename(file.getOriginalFilename());
            image.setMimeType(file.getContentType());
            image.setContent(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException e) {
            log.error("ImageToBytes error : ", e.getMessage());
            e.printStackTrace();
        }
        return image;
    }

    public ResponseEntity<List<ProductResponse>> getProductsResponse() {
        List<ProductResponse> results = repository.findAll().stream().map(value -> {
            ProductResponse response = new ProductResponse();
            response.setName(value.getName());
            response.setDescription(value.getDescription());
            response.setPrice(value.getPrice());

            String base64Image = Base64.encodeBase64String(value.getImage().getContent().getData());
            response.setImage(base64Image);
            response.setMimeType(value.getImage().getMimeType());
            return response;
        }).toList();
        return ResponseEntity.ok().body(results);

    }

    public List<ProductResponse> getProducts() {
        List<ProductResponse> results = repository.findAll().stream().map(value -> {
            ProductResponse response = new ProductResponse();
            response.setName(value.getName());
            response.setDescription(value.getDescription());
            response.setPrice(value.getPrice());

            String base64Image = Base64.encodeBase64String(value.getImage().getContent().getData());
            response.setImage(base64Image);
            response.setMimeType(value.getImage().getMimeType());
            return response;
        }).toList();
        return results;

    }

}
