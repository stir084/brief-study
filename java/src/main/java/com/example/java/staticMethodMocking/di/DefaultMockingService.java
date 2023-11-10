package com.example.java.staticMethodMocking.di;

import com.example.java.staticMethodMocking.StaticMethodMockingRepository;
import org.springframework.stereotype.Component;

@Component
public class DefaultMockingService implements MockingService {
    @Override
    public String connect() {
        return StaticMethodMockingRepository.staticMethod();
    }
}
