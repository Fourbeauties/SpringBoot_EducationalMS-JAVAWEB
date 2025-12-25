package com.example.demo.mapper;

import com.example.demo.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色id 查询权限
     * @param id
     * @return
     */
    List<Permission> findPersById(int id);
}
