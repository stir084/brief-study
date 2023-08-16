package com.example.java;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {

    @Bean
    public SampleBean sampleBean() {
        return new SampleBean();
    }
    public static class SampleBean {

    }

}