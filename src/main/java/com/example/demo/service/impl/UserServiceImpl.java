package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.constant.MessageConstant;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Role;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.dto.*;
import com.example.demo.pojo.vo.*;
import com.example.demo.result.Result;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 登录
     * @param loginDTO
     */
    @Override
    public Result login(LoginDTO loginDTO, HttpSession session) {

        String username=loginDTO.getUsername();
        // 根据用户名查询用户信息
        UserVO user = userMapper.findUserByUsername(username);

        if (user == null) {
           return new Result(1,"用户不存在",null);
        }
        //验证密码
        if(user.getPassword().equals(loginDTO.getPassword())){
            session.setAttribute("login_user",user);
            session.setAttribute("user_pers",user.getPermissions());
            return new Result(0,"ok",null);

        }else {
            return new Result(1,"密码错误",null);
        }
    }

    @Override
    public TeacherVO findById(Integer id) {
       return  teacherMapper.findTeaById(id);
    }

    @Override
    public Student findStuById(Integer id) {
        return studentMapper.findStuByID(id);
    }

    /**
     * 分页查询用户信息
     * @param userPageQueryDTO
     * @return
     */
    @Override
    public List<UserAdminVO> findAllUsers(UserPageQueryDTO userPageQueryDTO) {

       List<UserAdminVO>  userVOList=userMapper.findAllUsers(userPageQueryDTO);
       for(UserAdminVO userAdminVO:userVOList){
           StringBuilder sb = new StringBuilder();
           for(RoleVO role:userAdminVO.getRoles()){
               sb.append(role.getName()+"|");
           }
           userAdminVO.setRoleString(sb.toString());
       }
       return userVOList;
    }

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @Override
    @Transactional
    public Result addUser(UserDTO userDTO) {
        if(userDTO==null){
            return new Result(1,"新增失败，请正确填写信息",null);
        }
        if(userDTO.getUsername()==null||userDTO.getPassword()==null||userDTO.getRolesId()=="-1"){
            return new Result(1,"请正确填写用户名，密码，权限",null);
        }
        if(userDTO.getLocked()==null){
            userDTO.setLocked("0");
        }
        //判断用户名是否重复
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",userDTO.getUsername());
        User isUser = userMapper.selectOne(wrapper);
        if(isUser!=null){
            return new Result(1,"用户名不能重复",null);
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        userMapper.addUser(user);
        int userId= user.getId();
        //获取用户的权限 教师或者学生 添加至对应的表
        Role role = roleMapper.selectById(userDTO.getRolesId());
        if(role.getName().equals("教师")){
            //教师表添加数据
            Teacher teacher = new Teacher();
            teacher.setUsername(userDTO.getUsername());
            teacher.setName(userDTO.getUsername());
            teacher.setSex("男");
            teacherMapper.insert(teacher);
            userMapper.addEasUserRole(userId,3);
        }else if(role.getName().equals("学生")){
            Student student = new Student();
            student.setUsername(userDTO.getUsername());
            student.setName(userDTO.getUsername());
            student.setSex("男");
            studentMapper.insert(student);
            userMapper.addEasUserRole(userId,2);
        }
//        userMapper.addEasUserRole(user.getId(),userDTO.getRoleIds());
        return new Result(0,"添加成功",null);
    }

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @Override
    @Transactional
    public Result register(RegisterDTO registerDTO) {
        //两次密码不一致
        if(!registerDTO.getPassword().equals(registerDTO.getPassword2())){
            return new Result(1,"密码不一致",null);
        }
        //用户名不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",registerDTO.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null){
            return new Result(1,"用户名不能重复",null);
        }
        //注册用户,添加学生信息学生姓名，默认用户名
        User newUser = new User();
        BeanUtils.copyProperties(registerDTO,newUser);
        newUser.setLocked("0");
        userMapper.insert(newUser);
        //该用户默认分配学生权限
        userMapper.addEasUserRole(newUser.getId(),2);
        //往学生表中添加数据
        Student student = new Student();

        student.setUsername(registerDTO.getUsername());
        student.setName(registerDTO.getUsername());//姓名默认未用户名
        student.setSex("男");
        studentMapper.insert(student);
        return new Result(0,"注册成功",null);
    }

    /**
     * 修改密码
     * @param changePwdDTO
     * @return
     */
    @Override
    public Result passwordRest(ChangePwdDTO changePwdDTO,HttpSession session) {
        UserVO login_user = (UserVO) session.getAttribute("login_user");
        Integer id = login_user.getId();
        User user = userMapper.selectById(id);
        if(changePwdDTO==null){
            return new Result(1,"请正确填写密码",null);
        }
        if(changePwdDTO.getOldPassword()==null||changePwdDTO.getNewPassword1()==null||changePwdDTO.getNewPassword2()==null){
            return new Result(1,"密码不能为空",null);
        }
        if(!changePwdDTO.getOldPassword().equals(user.getPassword())){
            return new Result(1,"旧密码不一致",null);
        }
        if(!changePwdDTO.getNewPassword1().equals(changePwdDTO.getNewPassword2())){
            return new Result(1,"新密码不一致",null);
        }
        user.setPassword(changePwdDTO.getNewPassword1());
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("id",id);
        userMapper.update(user,objectQueryWrapper);
        return new Result(0,"修改密码成功",null);
    }

    /**
     * 根据学生id查找学生
     * @param id
     * @return
     */
    @Override
    public StudentVO findStuVOById(Integer id) {
        return studentMapper.findStuVOById(id);
    }

    /**
     * 修改用户信息
     * @param modifyUserDTO
     * @param session
     * @return
     */
    @Override
    public Result modifyUser(ModifyUserDTO modifyUserDTO, HttpSession session) {


        //判断用户的角色
        UserVO user = (UserVO) session.getAttribute("login_user");
        List<RoleVO> roles = user.getRoles();
        String roleName=null;
        if(roles!=null){
            roleName=roles.get(0).getName();
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setUsername(modifyUserDTO.getUsername());
        //学生
        if(roleName.equals("学生")){
            if(modifyUserDTO.getName()==null){
                return new Result(1,"姓名",null);
            }
            Student oldStu = studentMapper.findByUsername(user.getUsername());
            Student newStu = new Student();
            BeanUtils.copyProperties(modifyUserDTO,newStu);
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            newStu.setId(oldStu.getId());
            queryWrapper.eq("id",oldStu.getId());
            studentMapper.update(newStu,queryWrapper);
            return new Result(0,"修改成功",null);
        }else if(roleName.equals("教师")) {
            if(modifyUserDTO.getName()==null||modifyUserDTO.getEducation()==null){
                return new Result(1,"姓名，学历为必填项",null);
            }
            Teacher oldTea = teacherMapper.findTeaUsername(user.getUsername());
            Teacher newTea = new Teacher();
            BeanUtils.copyProperties(modifyUserDTO,newTea);
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            newTea.setId(oldTea.getId());
            queryWrapper.eq("id",oldTea.getId());
            teacherMapper.update(newTea,queryWrapper);
            return new Result(0,"修改成功",null);

        }
        return new Result(1,"管理员不需要修改信息",null);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Result batchDelete(int[] ids) {
        for(int id:ids){
            //删除user 表
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            userMapper.delete(queryWrapper);
            //删除user_role
            roleMapper.deleteEasUserRoleByRid(id);
        }
        return new Result(0,"删除成功",null);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public UserVO findUserById(int id) {
        return userMapper.findUserByUserId(id);
    }

    /**
     * 修改用户信息
     * @param userDTO
     * @return
     */
    @Override
    @Transactional
    public Result edit(UserDTO userDTO) {

        if(userDTO==null){
            return new Result(1,"新增失败，请正确填写信息",null);
        }
        if(userDTO.getUsername().equals("") ||userDTO.getPassword().equals("") ||userDTO.getRolesId().equals("-1")){
            return new Result(1,"请正确填写用户名，密码，权限",null);
        }
        if(userDTO.getLocked()==null){
            userDTO.setLocked("0");
        }
        //先删除 eas_user信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userDTO.getId());
        userMapper.delete(queryWrapper);
        //删除eas_user_role
        roleMapper.deleteEasUserRoleByRid(userDTO.getId());

        //判断用户名是否重复
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("username",userDTO.getUsername());
        User isUser = userMapper.selectOne(wrapper);
        if(isUser!=null){
            return new Result(1,"用户名不能重复",null);
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);

        userMapper.addUser(user);
        int userId= user.getId();
        //获取用户的权限 教师或者学生 添加至对应的表
        Role role = roleMapper.selectById(userDTO.getRolesId());
        if(role.getName().equals("教师")){
            //教师表添加数据
            Teacher teacher = new Teacher();
            teacher.setUsername(userDTO.getUsername());
            teacher.setName(userDTO.getUsername());
            teacherMapper.insert(teacher);
            userMapper.addEasUserRole(userId,3);
        }else if(role.getName().equals("学生")){
            Student student = new Student();
            student.setUsername(userDTO.getUsername());
            student.setName(userDTO.getUsername());
            studentMapper.insert(student);
            userMapper.addEasUserRole(userId,2);
        }
//        userMapper.addEasUserRole(user.getId(),userDTO.getRoleIds());
        return new Result(0,"修改成功",null);
    }




}
