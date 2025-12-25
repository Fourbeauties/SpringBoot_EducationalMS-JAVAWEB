package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.pojo.Permission;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserAdminVO implements Serializable {

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否锁定
     */
    private String locked;


    /**
     *  权限id
     */
    private List<RoleVO> roles;

    /**
     * 角色名称
     */
    private String roleString;

    /**
     * 注册日期
     */
    private LocalDate regDate;



}
