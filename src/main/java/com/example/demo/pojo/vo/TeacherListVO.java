package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.pojo.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data

public class TeacherListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //teacher ID

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

    /**
     * 用户信息
     */
    User user;

}
