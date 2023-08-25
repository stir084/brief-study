package com.example.java.mvcgrammers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MVCService {

    private final MVCRespository mvcRespository;
    public String test(){
        return "service-str";
    }

    public MVCDto test2(){
        String temp = mvcRespository.save();
        MVCDto mvcDto = new MVCDto();
        mvcDto.setName(temp);
        return mvcDto;
    }
}
