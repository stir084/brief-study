package com.example.java;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class WebfluxController {
    private final WebfluxService webfluxService;
    @GetMapping("/webflux")
    public Mono<String> webflux() {
        System.out.println(Thread.currentThread().getName() + "Controller Start");
        System.out.println(webfluxService.invokeVmScript());
        System.out.println(Thread.currentThread().getName() + "Controller Stop");
        /*System.out.println(webfluxService.invokeVmScript().subscribe(result -> {
            System.out.println(Thread.currentThread().getName() + "Subscriber");
        }));*/
        return Mono.just("Hello, world!");
    }

}