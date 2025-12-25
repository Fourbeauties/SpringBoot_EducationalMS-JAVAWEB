package com.example.demo.controller;



import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.*;
import com.example.demo.pojo.vo.*;
import com.example.demo.result.LayuiResult;
import com.example.demo.result.Result;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Controller
@RequestMapping
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;


    //老师，管理员、学生、老师可能是管理员，但是学生一般不是管理员，二者会冲突
    @RequestMapping("/easLogin/easUser/basicInformationIndex")
    public ModelAndView edit(HttpSession session){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> user = loginUser.getRoles();
        String roleName=null;
        if(user!=null){
            roleName=user.get(0).getName();
        }
        ModelAndView modelAndView = new ModelAndView();
        //如果是老师
        if(!roleName.equals("学生")) {
            TeacherVO teacher = userService.findById(loginUser.getId());
            modelAndView.addObject("data",teacher);
            modelAndView.addObject("code",3);
        }
        else { //如果是学生
            StudentVO student= userService.findStuVOById(loginUser.getId());
            modelAndView.addObject("data",student);
            modelAndView.addObject("code",2);
        }
        modelAndView.setViewName("/basicInformation");
        return modelAndView;
    }


    /**
     *
     * @return
     */
    @RequestMapping("/easLogin/easUser/index")
    public String index(){
        return "/system/user/index";
    }



    /**
     *跳转新增用户页面
     * @return
     */
    @GetMapping("/easUser/form")
    public String frorm(){
        return "/system/user/form";
    }


    /**
     * 查询所有的用户
     * @return
     */
    @RequestMapping("/easUser/list")
    @ResponseBody
    public LayuiResult list(UserPageQueryDTO userPageQueryDTO){
        List<UserAdminVO> userVOS = userService.findAllUsers(userPageQueryDTO);
        return LayuiResult.ok(userVOS,userVOS.size());
    }


    /**
     * 新增学生
     * @param userDTO
     * @return
     */
    @PostMapping("/easUser/add")
    @ResponseBody
    public Result addUser(UserDTO userDTO){
        log.info("开始新增用户:{}",userDTO);
        Result result=userService.addUser(userDTO);
        return result;
    }

    @GetMapping("easLogin/easUser/passwordRestIndex")
    public String passwordRestIndex(){
        return "/system/user/changePwd";
    }

    /**
     * 注册
     * @return
     */
    @GetMapping("/easRegister/registerForm")
    public String registerForm(){
        return "registerForm";
    }

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @RequestMapping("/easRegister/registerUser")
    @ResponseBody
    public Result registerUser(RegisterDTO registerDTO){
        return userService.register(registerDTO);
    }

    @RequestMapping("/easUser/passwordRest")
    @ResponseBody
    public Result passwordRest(ChangePwdDTO changePwdDTO,HttpSession session){
        return userService.passwordRest(changePwdDTO,session);
    }

    @GetMapping("/easLogin/easUser/easUser/basicForm")
    public String getBasicForm(){
        return "/system/user/form2";
    }

    @PostMapping("/easUser/modifyInformation")
    @ResponseBody
    public Result  modifyInformation(ModifyUserDTO modifyUserDTO,HttpSession session){
        return userService.modifyUser(modifyUserDTO,session);
    }

    /**
     * 删除角色
     */
    @RequestMapping("/easUser/batchDelete")
    @ResponseBody
    public Result batchDelete(int  ids[]){
        return userService.batchDelete(ids);
    }

    @RequestMapping("/easUser/edit")
    @ResponseBody
    public Result edit(UserDTO userDTO){
        return userService.edit(userDTO);
    }


    /**
     * 修改用户信息
     * @param session
     * @return
     */
    @GetMapping("/easUser/getBasicInformation")
    @ResponseBody
    public Object getBasicInformation(HttpSession session){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> user = loginUser.getRoles();
        String roleName=null;
        if(user!=null){
            roleName=user.get(0).getName();
        }
        //如果是老师
        if(roleName.equals("教师")) {
            TeacherVO teacher = userService.findById(loginUser.getId());
            teacher.setCode(3);
            return teacher;
        }
        else if (roleName.equals("学生")){ //如果是学生
            StudentVO student= userService.findStuVOById(loginUser.getId());
                student.setCode(2);
                 return student;
        }else {
            return null;
        }
    }
    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/easUser/view")
    @ResponseBody
    public UserVO view(int id){
        return userService.findUserById(id);
    }


}

