package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.ModifyUserDTO;
import com.example.demo.pojo.dto.UserPageQueryDTO;
import com.example.demo.pojo.vo.UserAdminVO;
import com.example.demo.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据username 查询用户
     * @param username
     */
    UserVO findUserByUsername(String username);


    /**
     * 分页查询用户信息
     * @param
     * @param userPageQueryDTO
     * @return
     */
    List<UserAdminVO> findAllUsers(@Param("userPageQueryDTO") UserPageQueryDTO userPageQueryDTO);

    /**
     * 新增用户
     * @param user
     */
    void addUser(User user);

    //新增用户
    void addEasUserRole(@Param("userId") Integer id, @Param("roleId") int roleIds);

    /**
     *
     * @return
     */
    UserVO findUserByUserId(int id);


}
