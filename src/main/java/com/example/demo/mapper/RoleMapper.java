package com.example.demo.mapper;

import com.example.demo.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有的权限
     * @return
     */
    @Select("select * from eas_role")
    List<Role> findAll();

    @Delete("delete from eas_role_permission where eas_role_permission.eas_role_id=#{id}")
    void deleteEasRolePermissionByRId(int id);

    /**
     * 新增eas_role_permission
     * @param roleId
     * @param id
     */
    @Insert("insert into eas_role_permission(eas_role_id,eas_permission_id) values(#{roleId},#{pid})")
    void addEasRolePermission(@Param("roleId") int roleId, @Param("pid") int id);

    /**
     * 删除easUserRole表
     * @param id
     */
    @Delete("delete from eas_user_role where eas_user_role.eas_user_id=#{id}")
    void deleteEasUserRoleByRid(int id);

}
