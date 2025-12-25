package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.pattern.PathPattern;

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
@TableName("eas_course")
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开设日期
     */
    private LocalDate startDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 总课时
     */
    private Integer classHour;

    /**
     * 考核方式
     */
    private String testMode;

    /**
     * 学生数量
     */
    private Integer studentNum;

    /**
     * 选课人数
     */
    private Integer choiceNum;

    /**
     * 是否是完成的课程
     */
    private Integer complete;

    /**
     * 外键-教师号
     */
    private Integer tId;

    /**
     *教师姓名
     */

    private String teacherName;

    /**
     * 外键-课程号
     */
    private Integer baseCourseId;

    /**
     * 基础课程名车给你
     */
    private String courseName;
}
