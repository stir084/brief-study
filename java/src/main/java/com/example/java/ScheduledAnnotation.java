package com.example.java;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class ScheduledAnnotation {

    @Async // 안하면 쓰레드 물고있음
    // 10초마다 실행된다고해서 스프링이 자연스럽게 스케줄을 실행 시켜주는게 아니다.
    // 쓰레드 하나가 스케줄러때문에 돌고있다고 보면된다. 그래서 @Scheduled와 @Async를 같이 써야한다.
    //@Scheduled(fixedRate = 3000) // 3초마다 실행
    public void scheduledAsyncTask() {
        System.out.println("Scheduled async task is running on thread: " + Thread.currentThread().getName());
    }
}