package com.example.java.mvcgrammers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MvcServiceTest {

    @Mock
    private MVCService mvcService;

    // Service를 Mock으로 만들어서 테스트
    @Test
    void test() {
        Mockito.when(mvcService.test()).thenReturn("mocked-service-str");
        String result = mvcService.test();
        Assertions.assertEquals("mocked-service-str", result);
    }
}
