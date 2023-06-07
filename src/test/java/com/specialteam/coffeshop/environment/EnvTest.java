package com.specialteam.coffeshop.environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class EnvTest {
    @Autowired
    private Environment environment;

    @Test
    public void envTestShouldReturnTest() {
        assertNotNull(environment.getProperty("test"));
    }

}
