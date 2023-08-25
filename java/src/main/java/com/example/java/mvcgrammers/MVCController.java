package com.example.java.mvcgrammers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MVCController {

    private final MVCService mvcService;
    @GetMapping("/mvc")
    public String mvc(){
        String temp = mvcService.test();
        return temp;
    }

    @GetMapping("/mvc2")
    public MVCDto mvc2(){
        MVCDto mvcDto = new MVCDto();
        mvcDto.setName("stir");
        return mvcDto;
    }


}
