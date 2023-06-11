package com.specialteam.coffeeshop.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AppUtilsTest {

    static MockedStatic<AppUtils> utils;

    @BeforeAll
    static void init() {
        utils = Mockito.mockStatic(AppUtils.class);
    }

    @AfterAll
    static void close() {
        utils.close();
    }

    @Test
    void testAppUtilsMockIsNotNull() {
        assertNotNull(utils);
    }

    @Test
    void testConcatFullname() {
        String a = "bujank", b = "lapuk";
        utils.when(() -> AppUtils.concatFullname(a, b)).thenReturn("bujank lapuk");

        assertEquals(AppUtils.concatFullname(a, b), "bujank lapuk");
    }

}
