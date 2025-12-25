package com.example.demo.pojo.dto;

import lombok.Data;

@Data
public class ScorePageQueryDTO {



    private int result; //0 未通过  1 已通过 2 全部

    private int sid;

    private String baseCourseId;//基础课程id

}
