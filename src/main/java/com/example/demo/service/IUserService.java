package com.example.demo.service;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.*;
import com.example.demo.pojo.vo.StudentVO;
import com.example.demo.pojo.vo.TeacherVO;
import com.example.demo.pojo.vo.UserAdminVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     * @param loginDTO
     */
    Result login(LoginDTO loginDTO, HttpSession session);

    /**
     * 根据id 查询老师
     * @param id
     * @return
     */
    TeacherVO findById(Integer id);

    /**
     * 根据id 查询学生
     * @param id
     * @return
     */
    Student findStuById(Integer id);

    /**
     * 查询所有的用户
     * @param userPageQueryDTO
     * @return
     */
    List<UserAdminVO> findAllUsers(UserPageQueryDTO userPageQueryDTO);

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    Result addUser(UserDTO userDTO);

    /**
     * 注册用户
     * @param registerDTO
     * @return
     */
    Result register(RegisterDTO registerDTO);

    /**
     * 修改密码
     * @param changePwdDTO
     * @return
     */
    Result passwordRest(ChangePwdDTO changePwdDTO,HttpSession session);

    /**
     * 找学生
     * @param id
     * @return
     */
    StudentVO findStuVOById(Integer id);

    /**
     * 修改用户信息
     * @param modifyUserDTO
     * @param session
     * @return
     */
    Result modifyUser(ModifyUserDTO modifyUserDTO, HttpSession session);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    Result batchDelete(int[] ids);

    UserVO findUserById(int id);

    /**
     * 编辑用户信息
     * @param userDTO
     * @return
     */
    Result edit(UserDTO userDTO);


}
