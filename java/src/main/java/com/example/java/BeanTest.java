package com.example.java;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanTest {
    // SampleBean에서 new로 생성하더라도 bean은 싱글톤이므로 같은 객체를 보장 받는다.
    @Autowired
    private AppConfig.SampleBean sampleBean;

    @PostConstruct
    public void init() {
        SameClass sameClass1 = new SameClass();
        SameClass sameClass2 = new SameClass();
        System.out.println("과연 같을까?");
        AppConfig.SampleBean sampleBean1 = sampleBean;
        AppConfig.SampleBean sampleBean2 = sampleBean;
        System.out.println(sameClass1 == sameClass2);
        System.out.println(sampleBean1 == sampleBean2);
    }

    public static class SameClass {

    }



}
