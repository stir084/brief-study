package com.example.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolTaskScheduler1 {

    // 스케줄링 병렬 작업
    // @Scheduled는 내부적으로 ThreadPoolTaskScheduler를 사용하기 때문에 아래와 같은 설정이 가능하다.
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5); // 스케줄링 작업을 처리할 스레드 개수 설정
        scheduler.initialize();
        return scheduler;
    }

    @Async
    //@Scheduled(fixedRate = 1000) // 1초마다 실행 // 주석 해제
    public void scheduledAsyncTask() {
        System.out.println("Scheduled async task is running on thread: " + Thread.currentThread().getName());
    }
}