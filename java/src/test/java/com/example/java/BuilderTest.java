package com.example.java;

import lombok.Builder;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

public class BuilderTest {

   /* @Test
    void test(){
        BuilderClass.BuilderClassBuilder gggg = BuilderClass.builder()
                .name("gggg");
    }*/
    @Builder
    public static class BuilderClass {
        private String name;
    }

    @Test
    void tttt(){
        long unixTime = 1698734281L; // 예시로 제공된 Unix 시간

        Date date = convertUnixTimeToDateTime(unixTime);
        System.out.println(date);
    }
    public static Date convertUnixTimeToDateTime(long unixTime) {
        // Unix 시간을 밀리초 단위로 변환
        Date date = new Date(unixTime);

        return date;
    }
}
