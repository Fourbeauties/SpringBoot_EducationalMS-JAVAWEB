package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@TableName("eas_teacher")
@ToString
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    private LocalDate birthday;

    /**
     * 电话
     */
    private String phone;

    /**
     * 学历
     */
    private String education;

    /**
     * 座右铭
     */
    private String motto;


}
