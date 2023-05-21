package com.specialteam.coffeshop.product.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.specialteam.coffeshop.product.model.Product;

@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void whenCalledFindAll_shouldReturnValidSize() {
        /// If data is not added
        int actualSize = 3;
        int size = repository.findAll().size();
        assertEquals(actualSize, size);
    }

    @Test
    public void whenCalledFindByName_shouldRetunNotNull() {
        /// Find product with name "Test"
        Optional<Product> product = repository.findByName("Test");
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    public void whenCalledEditAvailability_shouldRetunFalse() {
        /// Find product with name "Test"
        Optional<Product> product = repository.findByName("Test");
        product.get().setIsAvailable(false);

        repository.save(product.get());

        assertThat(product.get().getIsAvailable()).isFalse();
    }
}
