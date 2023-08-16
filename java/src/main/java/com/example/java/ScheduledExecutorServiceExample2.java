package com.example.java;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample2 implements Runnable {
    @Override
    public void run() {
        // 작업 내용
        System.out.println("zzz");
    }

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledExecutorServiceExample2 task = new ScheduledExecutorServiceExample2();
        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
        // 스프링에서 만약에 사용자별로 스케줄링을 구현하려고하면 executor를 매번 하나씩 생성해줘야한다.
        // 그러므로 HashMap을 만들어서 key값은 유저 아이디를 넣고 executor를 값으로 넣어줘야한다.
        // 근데 그건 ThreadPoolTaskScheduler로 구현해도 마찬가지다.

        // 스케줄링이 끝난건 executor를 멈추고 객체 소멸까지 시켜줘야한다(물론 객체 소멸은 gc에 의해 가능할 수도 있다.)
    }
}