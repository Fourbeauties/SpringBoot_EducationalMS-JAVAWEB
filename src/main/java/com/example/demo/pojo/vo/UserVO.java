package com.example.demo.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVO  implements Serializable {

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
     * 角色名称
     */
    private String roleName;

    /**
     *  权限id
     */
    private List<RoleVO> roles;

    /**
     * 具体的权限
     */
    List<Permission> permissions;

}
