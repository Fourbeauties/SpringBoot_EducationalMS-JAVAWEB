package com.example.demo.service;

import com.example.demo.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.result.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据角色id查询权限
     * @param id
     * @return
     */
    List<Permission> findPersByRoleId(int id);

}
