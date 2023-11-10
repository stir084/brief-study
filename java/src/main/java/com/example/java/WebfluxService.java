package com.example.java;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebfluxService {
    public Mono<String> invokeVmScript() {
        System.out.println(Thread.currentThread().getName() + "Service");

        WebClient webClient = WebClient.create();
        webClient
                .get()
                .uri("https://jsonplaceholder.typicode.com/todos")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(result -> {
                    System.out.println(Thread.currentThread().getName() + "Service");
                    System.out.println(result.length());
                });
        return Mono.just("dfdf");
    }
}
