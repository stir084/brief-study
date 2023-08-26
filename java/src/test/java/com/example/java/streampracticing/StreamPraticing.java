package com.example.java.streampracticing;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StreamPraticing {
    private List<StreamDto> streamDtoList;
    private Map<Long, String> streamDtoMap;
    @BeforeEach
    void init(){
        StreamDto streamDto1 = new StreamDto(1L, "철수");
        StreamDto streamDto2 = new StreamDto(2L, "짱구");
        StreamDto streamDto3 = new StreamDto(3L, "철수");
        streamDtoList = new ArrayList<>();
        streamDtoList.add(streamDto1);
        streamDtoList.add(streamDto2);
        streamDtoList.add(streamDto3);

        streamDtoMap = new HashMap<>();
        streamDtoMap.put(1L, "철수");
        streamDtoMap.put(2L, "짱구");
        streamDtoMap.put(3L, "철수");

    }

    //List Stream filter
    @Test
    void test(){
        long 철수몇명 = streamDtoList.stream()
                .filter(a -> a.getName().equals("철수"))
                .count();
        Assertions.assertThat(철수몇명).isEqualTo(2) ;

        Long 첫번째철수아이디 = streamDtoList.stream()
                .filter(a -> a.getName().equals("철수"))
                .findFirst().get()
                .getId();
        Assertions.assertThat(첫번째철수아이디).isEqualTo(1L);
    }

    //Map Stream filter
    @Test
    void test2(){
        long 철수몇명 = streamDtoMap.entrySet().stream()
                .filter(streamDto -> streamDto.getValue().equals("철수"))
                .count();
        Assertions.assertThat(철수몇명).isEqualTo(2) ;

        Long 첫번째철수아이디 = streamDtoMap.entrySet().stream()
                .filter(streamDto -> streamDto.getValue().equals("철수"))
                .findFirst().get()
                .getKey();
        Assertions.assertThat(첫번째철수아이디).isEqualTo(1L);
    }

    @Test
    void test3(){
        Map<String, Integer> names = new HashMap<>();
        names.put("stir", 1);
        names.entrySet().stream()
                .map(s -> names.put("stir", 2));

        //stir에 2 삽입을 했지만 최종 연산을 넣어주지 않아 실행하지 않음.
        System.out.println();
    }

    /*
     return segmentResponse.getResults()
                .stream()
                .map(SegmentDto::setSegmentDto)
                .toList();
     */
}
