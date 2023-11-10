package com.example.java.staticMethodMocking;

import org.springframework.stereotype.Repository;

@Repository
public class StaticMethodMockingRepository {
    public static String staticMethod() {
        return "dddd";
    }
    public String noneStaticMethod() {
        return "dddd";
    }
    public String getStaticMethodReturn() {
        return staticMethod();
    }
}
