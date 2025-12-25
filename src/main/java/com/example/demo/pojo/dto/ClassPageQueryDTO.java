package com.example.demo.pojo.dto;

import lombok.Data;

@Data
public class ClassPageQueryDTO {

    private int page;

    private int limit;

    private String classes;
}
