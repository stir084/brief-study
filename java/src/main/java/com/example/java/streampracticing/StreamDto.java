package com.example.java.streampracticing;

import lombok.Data;

import java.util.Map;

@Data
public class StreamDto {
    private Long id;
    private String name;
    public StreamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StreamDto createStreamDto2(Map.Entry<Long, String> entry) {

        return new StreamDto( entry.getKey(), entry.getValue());
    }
}