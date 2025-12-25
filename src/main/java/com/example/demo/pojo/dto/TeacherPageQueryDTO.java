package com.example.demo.pojo.dto;

import lombok.Data;

@Data
public class TeacherPageQueryDTO {


    private String name;

    private String phone;

    /**
     * 学历
     */
    private String education;

}
