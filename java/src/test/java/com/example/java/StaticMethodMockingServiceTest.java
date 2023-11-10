package com.example.java;

import com.example.java.staticMethodMocking.StaticMethodMockingRepository;
import com.example.java.staticMethodMocking.StaticMethodMockingService;
import com.example.java.staticMethodMocking.di.MockingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class StaticMethodMockingServiceTest {
    @InjectMocks
    private StaticMethodMockingService staticMethodMockingService;

    @Mock
    private StaticMethodMockingRepository staticMethodMockingRepository;

    @Mock
    private MockingService mockingService;
    @Test
    @DisplayName("성공적으로 응답한다")
    public void it_responds_successfully() throws Exception {
        //when(staticMethodMockingRepository.noneStaticMethod()).thenReturn("zzzz"); //성공
        when(StaticMethodMockingRepository.staticMethod()).thenReturn("zzzz"); //실패
        staticMethodMockingService.test();
        /*
        when() requires an argument which has to be 'a method call on a mock'.

        when에 static method를 전달하면 mock위에서 실행될 메소드가 아니라서 위 에러가 발생한다.
        왜냐면 @Mock이 붙은건 프록시 객체를 만들어주는데 Static 메소드는 프록시 객체가 원본 객체를 상속할 때 오버라이드 하지 않기 때문임.
        */
        // 해결 방법 mockito-inline, powermock, DI 구현
    }

    @Test
    public void test2() { // static 메소드를 일반 메소드로 감싸 버리는 방식.
        when(staticMethodMockingRepository.getStaticMethodReturn()).thenReturn("zzzz"); //실패
        staticMethodMockingService.test2();
    }

    @Test
    public void test3() {
        // static 메소드를 일반 메소드로 감싼걸 DI로 구현한 형태
        // 위의 방식이 더 간단해보이긴하지만
        // DI로 감싸면 이곳저곳 다 쓸 수 있끼 때문에 그런듯?
        // static 메소드를 일반 메소드로 감싸면 그 클래스 안에서밖에 못쓰는게 문제기 때문임.
        when(mockingService.connect()).thenReturn("zzzz");
        staticMethodMockingService.test3();
    }
}
