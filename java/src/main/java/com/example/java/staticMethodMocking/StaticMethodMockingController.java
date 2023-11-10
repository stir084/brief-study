package com.example.java.staticMethodMocking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StaticMethodMockingController {
    private final StaticMethodMockingService staticMethodMockingService;
    @GetMapping("/mocking")
    public String test(){
        String zz = staticMethodMockingService.test();
        System.out.println(zz);
        return zz;
    }

    @GetMapping("/mocking2")
    public String test2(){
        System.out.println();
        return "dfdfd";
    }
}
