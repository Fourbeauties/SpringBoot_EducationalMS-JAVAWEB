package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Getter
@Setter
@TableName("eas_score")
public class ScoreVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 考试分数
     */
    private Integer score;

    /**
     * 考试结果
     */
    private String result;

    /**
     * 学生id
     */
    private Integer sId;

    /**
     * 教师
     */

    private String teacherName;


    /**
     * 开设日期
     */
    private LocalDate startDate;

    /**
     * 课程id
     */
    private Integer cId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 考试方式
     */
    private String testMode;


}
