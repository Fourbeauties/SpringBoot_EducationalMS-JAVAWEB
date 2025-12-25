package com.example.demo.service.impl;

import com.example.demo.pojo.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.result.Result;
import com.example.demo.service.INoticeService;
import com.example.demo.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    /**
     * 根据角色id查询权限
     * @param id
     * @return
     */
    @Override
    public List<Permission> findPersByRoleId(int id) {
        List<Permission> permissions = permissionMapper.findPersById(id);
        ArrayList<Permission> list = new ArrayList<>();
        for(Permission p:permissions){
            if(p.getParentid()==null){
                list.add(p);
            }
        }
        return list;
    }
}
