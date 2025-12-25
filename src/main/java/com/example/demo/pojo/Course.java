package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Data
@ToString
@TableName("eas_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开设日期
     */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
     * 外键-课程号
     */
    private Integer baseCourseId;


}
