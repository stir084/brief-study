package com.example.java.streampracticing;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Getter
@Setter
public class StreamDto {
    private Long id;
    private String name;
    public StreamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}