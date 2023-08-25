package com.example.java.streampracticing;

import lombok.Data;

@Data
public class StreamDto {
    private Long id;
    private String name;
    public StreamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}