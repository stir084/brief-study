package com.example.java;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledExecutorServiceExample1 {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    // 자바 기본 클래스로 기본적으로 5초마다 반복 실행
    //@PostConstruct 테스트 시 해제하고 해야함
    public void init() {
        scheduleAsyncTask();
    }

    @Async
    public void asyncTask() {
        System.out.println("Async task is running on thread: " + Thread.currentThread().getName());
    }

    public void scheduleAsyncTask() {
        executorService.scheduleAtFixedRate(() -> asyncTask(), 0, 5, TimeUnit.SECONDS);
    }
}