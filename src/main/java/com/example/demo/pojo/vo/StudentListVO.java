package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.pojo.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class StudentListVO  implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 座右铭
     */
    private String motto;


    private User user;






}
