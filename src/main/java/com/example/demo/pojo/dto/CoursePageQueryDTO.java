package com.example.demo.pojo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CoursePageQueryDTO {



    private int isAll=1;

    private String searchKey=null;//关键词

    private int studentId;

    private int teacherId;

    private String courseName;

    private String teacherName;


}
