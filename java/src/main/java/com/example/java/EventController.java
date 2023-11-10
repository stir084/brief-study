package com.example.java;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
public class EventController {

    private final ApplicationContext applicationContext;

    @Autowired
    public EventController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/trigger-event")
    @ResponseBody
    public String triggerEvent() {
        applicationContext.publishEvent(new CustomEventListener.CustomEvent(this, "Event triggered from URL"));
        return "Event triggered!";
    }

    @GetMapping("/tttt")
    @ResponseBody
    public ResponseEntity<?> tttt(@Valid @RequestBody TTTT tttt){
        //TTTT tt = new TTTT("hi");
        System.out.println(tttt.getTest());
        return ResponseEntity.ok("zzz");
    }


    @Setter
    @Getter
    @NoArgsConstructor
    public static class TTTT{
        @NotNull
        private String test;

        public TTTT(String test){
            this.test = test;
        }
    }
}