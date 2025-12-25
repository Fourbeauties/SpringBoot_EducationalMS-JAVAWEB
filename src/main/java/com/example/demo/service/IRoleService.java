package com.example.demo.service;

import com.example.demo.pojo.Role;
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
public interface IRoleService extends IService<Role> {

    /**
     * 查找所有的角色
     * @return
     */
    List<Role> findAll();

    /**
     * 新增角色
     * @param role
     * @return
     */
    Result addRole(Role role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    Result batchDeleteRole(int[] ids);

    /**
     * 编辑角色
     * @param role
     * @return
     */
    Result editRole(Role role);

    /**
     * 角色授权
     * @param roleId
     * @param persIds
     * @return
     */
    Result assignPers(int roleId, List<Integer> persIds);
}
