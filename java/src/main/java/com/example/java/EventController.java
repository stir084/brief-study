package com.example.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
}