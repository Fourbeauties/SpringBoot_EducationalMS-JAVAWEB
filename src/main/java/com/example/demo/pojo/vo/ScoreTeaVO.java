package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class ScoreTeaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //成绩


    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话号码
     */
    private String phone;



    /**
     * 考试分数
     */
    private Integer score;

    /**
     * 考试结果
     */
    private String result;


    /**
     * 课程名称
     */
    private String courseName;




}
