package com.example.java.staticMethodMocking;

import com.example.java.staticMethodMocking.di.MockingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaticMethodMockingService {
    private final StaticMethodMockingRepository staticMethodMockingRepository;

    private final MockingService mockingService;
    public String test() {
        System.out.println("서비스입니다.");
        String s = StaticMethodMockingRepository.staticMethod();
        System.out.println(s);
        return s;
    }

    public String test2() {
        System.out.println("서비스입니다.");
        String s = staticMethodMockingRepository.getStaticMethodReturn();
        System.out.println(s);
        return s;
    }
    public String test3() {
        System.out.println("서비스입니다.");
        String s = mockingService.connect();
        System.out.println(s);
        return s;
    }
}
