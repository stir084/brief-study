package com.example.java.streampracticing;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StreamPraticing {
    private List<StreamDto> streamDtoList;
    @BeforeEach
    void init(){
        StreamDto streamDto1 = new StreamDto(1L, "철수");
        StreamDto streamDto2 = new StreamDto(2L, "짱구");
        StreamDto streamDto3 = new StreamDto(3L, "철수");
        streamDtoList = new ArrayList<>();
        streamDtoList.add(streamDto1);
        streamDtoList.add(streamDto2);
        streamDtoList.add(streamDto3);

    }
    //filter
    @Test
    void test(){
        Assertions.assertThat(streamDtoList.stream()
                .filter(a -> a.getName().equals("철수")).count()).isEqualTo(2) ;
        assertThat(streamDtoList.stream()
                .filter(a -> a.getName().equals("철수")).findFirst().get().getId(), is(1L));
    }


}
