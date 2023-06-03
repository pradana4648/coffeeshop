package com.specialteam.coffeshop.product.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialteam.coffeshop.product.dto.ProductDto;
import com.specialteam.coffeshop.product.dto.ProductRequestDto;
import com.specialteam.coffeshop.product.model.Cart;
import com.specialteam.coffeshop.product.model.Product;
import com.specialteam.coffeshop.product.model.ProductImage;
import com.specialteam.coffeshop.product.repository.CartRepository;
import com.specialteam.coffeshop.product.repository.ProductRepository;
import com.specialteam.coffeshop.user.model.User;
import com.specialteam.coffeshop.user.repository.UserRepository;
import com.specialteam.coffeshop.util.AppUtils;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProductDto> getProducts() {
        List<ProductDto> results = productRepository.findAll().stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setQuantity(product.getQuantity());
            dto.setPrice(product.getPrice());
            dto.setImageAsBase64(AppUtils.productImageAsBase64(product.getImage()));
            dto.setMimeType(product.getImage().getMimeType());
            dto.setFilename(product.getImage().getFilename());
            return dto;
        }).toList();
        return results;
    }

    public Map<String, Object> addProducts(String productJsonString, MultipartFile imageFile) throws Exception {
        Map<String, Object> result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            ProductRequestDto request = mapper.readValue(productJsonString, ProductRequestDto.class);

            Optional<Product> productByName = productRepository.findByName(request.getName());

            if (productByName.isPresent()) {
                result = new HashMap<>();
                result.put("message", String.format("product with name %s is exist", request.getName()));
                return result;
            } else {
                ProductImage image = new ProductImage();
                Product productEntity = new Product();

                image.setContent(new Binary(imageFile.getBytes()));
                image.setFilename(imageFile.getOriginalFilename());
                image.setMimeType(imageFile.getContentType());

                productEntity.setId(UUID.randomUUID().toString());
                productEntity.setName(request.getName());
                productEntity.setDescription(request.getDescription());
                productEntity.setQuantity(1);
                productEntity.setPrice(request.getPrice());
                productEntity.setImage(image);

                productRepository.save(productEntity);

                result = new HashMap<>();
                result.put("message", "product successfully added");
                return result;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public Map<String, Object> editProductAvailbility(String id) {
        Map<String, Object> result;
        Optional<Product> productById = productRepository.findById(id);

        if (!productById.isPresent()) {
            result = new HashMap<>();
            result.put("message", String.format("product with id %s not exist", id));
            return result;
        } else {
            Product product = productById.get();

            product.setIsAvailable(Boolean.TRUE);

            productRepository.save(product);

            result = new HashMap<>();
            result.put("message", String.format("success update availbility product id %s", id));

            return result;
        }

    }

    public Map<String, Object> addProductToCart(String username, ProductDto dto) {
        Map<String, Object> result = new HashMap<>();

        Optional<User> user = userRepository.findByUsername(username);
        List<Cart> carts = cartRepository.findByUserId(user.get().getUid());

        Cart cartEntity = new Cart();
        List<Product> products = new ArrayList<>();
        Product product = new Product();

        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());

        products.add(product);

        cartEntity.setUserId(user.get().getUid());
        cartEntity.setCartId(user.get().getUsername() + "-product");
        cartEntity.setProducts(products);

        cartRepository.save(cartEntity);

        result.put("message", "success add product");
        return result;
    }

    public Integer totalProductInCarts(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            List<Cart> carts = cartRepository.findByUserId(user.get().getUid());
            return carts.size();
        } else {
            return 0;
        }
    }

}
