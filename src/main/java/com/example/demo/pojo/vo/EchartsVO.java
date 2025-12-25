package com.example.demo.pojo.vo;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EchartsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalStuN;

    private int totalTeaN;

    private int totalMan;

    private int totalWoman;

    private int totalPass;

    private int totalNoPass;

}
