package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.pojo.Notice;
import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.pojo.vo.RoleVO;
import com.example.demo.result.Result;
import com.example.demo.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询所有的角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        List<Role> roles = roleMapper.findAll();
        return roles;
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public Result addRole(Role role) {
        if(role==null){
            return new Result(1,"角色名称不能为空",null);
        }
        if(role.getName()==null){
            return new Result(1,"角色名称不能为空",null);
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",role.getName());
        List<Role> roles = roleMapper.selectList(queryWrapper);
        if(roles.size()>=1){
            return new Result(1,"不能重复添加",null);
        }
        //角色不能重复添加
        roleMapper.insert(role);
        return new Result(0,"新增成功",null);
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Result batchDeleteRole(int[] ids) {

        for(int id:ids){
            //第一个删除eas_role 表
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            roleMapper.delete(queryWrapper);
            //删 除角色权限关联表
            roleMapper.deleteEasRolePermissionByRId(id);
        }
        return new Result(0,"ok",null);
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public Result editRole(Role role) {
        if(role.getId()!=null){ //查询这个角色是否已经 存在
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",role.getId());
            roleMapper.delete(queryWrapper);
        }
        int insert = roleMapper.insert(role);
        return new Result(0,"ok",null);
    }

    /**
     * 角色授权
     * @param roleId
     * @param persIds
     * @return
     */
    @Override
    @Transactional
    public Result assignPers(int roleId,List<Integer> persIds) {
        //1.删除eas_role_permission
        roleMapper.deleteEasRolePermissionByRId(roleId);
        //2.根据roleIds 父节点 查询所有的权限在新增eas_role_permission表
       if(persIds.size()!=0){
           for(Integer id:persIds){
               //根据权限的付姐点id 查询节点
               QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
               queryWrapper.eq("parentid",id);
               List<Permission> permissionList = permissionMapper.selectList(queryWrapper);
               //eas_role_permission 增加 父节点
               roleMapper.addEasRolePermission(roleId,id);
               for(Permission p:permissionList){
                   roleMapper.addEasRolePermission(roleId,p.getId());
               }
           }
       }
        return new Result(0,"授权成功",null);
    }
}
