package com.specialteam.coffeshop.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.specialteam.coffeshop.product.service.ProductService;

@Controller
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> getProducts() {
        return service.getProducts();
    }

    @PostMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> addProduct(@RequestParam(value = "product") String product,
            @RequestParam(value = "product_image") MultipartFile imageFile)
            throws Exception {
        return service.addProducts(product, imageFile);
    }

    @PostMapping(value = "/products/{product_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> editProductAvailbility(@PathVariable(name = "product_id") String id) {
        return service.editProductAvailbility(id);
    }

}
