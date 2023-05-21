package com.specialteam.coffeshop.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AppUtilsTest {

    @Test
    void whenCalledAppUtilsGeneratorId_shouldReturnValidIdBasedName() {
        String id = "teh tubruk cap kaki tiga";

        MockedStatic<AppUtils> apputilsMock = Mockito.mockStatic(AppUtils.class);

        apputilsMock.when(() -> AppUtils.productIdGenerator(id)).thenReturn("TTCPKT");

        assertThat(AppUtils.productIdGenerator(id)).isEqualTo("TTCPKT");

    }

}
