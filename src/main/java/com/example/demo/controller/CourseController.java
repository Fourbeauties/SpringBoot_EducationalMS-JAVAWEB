package com.example.demo.controller;


import com.example.demo.pojo.BaseCourse;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.dto.BaseCoursePageQueryDTO;
import com.example.demo.pojo.dto.CoursePageQueryDTO;
import com.example.demo.pojo.vo.CourseVO;
import com.example.demo.pojo.vo.RoleVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.LayuiResult;
import com.example.demo.result.Result;
import com.example.demo.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@Slf4j
@Controller
@RequestMapping
public class CourseController {

    private  final  String ROLE_NAME_STUDENT="学生";
    private  final  String ROLE_NAME_ADMIN="管理员";

    @Autowired
    private ICourseService iCourseService;


    @RequestMapping("easLogin/easBaseCourse/index")
    public String index(HttpSession session){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> roles = loginUser.getRoles();
        String roleName=null;
        if(roles!=null){
            roleName=roles.get(0).getName();
        }
        if(!roleName.contains(ROLE_NAME_ADMIN)){
            return "/refuse";
        }
        return "/system/baseCourse/index";
    }


    @RequestMapping("easLogin/easCourse/studentIndex")
    public String studentIndex(){
        return "/system/course/studentCourseIndex";
    }


    @RequestMapping("easLogin/easCourse/teacherIndex")
    public String teacherIndex(HttpSession session){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> roles = loginUser.getRoles();
        String roleName=null;
        if(roles!=null){
            roleName=roles.get(0).getName();
        }
        if(roleName.equals(ROLE_NAME_STUDENT)){
            return "/refuse";
        }
        return "/system/course/teacherCourseIndex";
    }

    @RequestMapping("easLogin/easCourse/adminIndex")
    public String adminIndex(HttpSession session){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> roles = loginUser.getRoles();
        String roleName=null;
        if(roles!=null){
            roleName=roles.get(0).getName();
        }
        if(!roleName.contains(ROLE_NAME_ADMIN)){
            return "/refuse";
        }
        return "/system/course/adminCourseIndex";
    }

//    @RequestMapping("easLogin/easCourse/myStudentIndex")
//    public String teacherIndex(){
//        return "/system/course/teacherCourseIndex";
//    }


    /**
     * 查询教师的课程信息
     * @return
     */
   @RequestMapping("/easCourse/getMyCourseList")
   @ResponseBody
   public LayuiResult getMyCourseList(CoursePageQueryDTO coursePageQueryDTO,HttpSession session){
       List<CourseVO> courseVOS = iCourseService.geMyCourseList(coursePageQueryDTO,session);
        return LayuiResult.ok(courseVOS,courseVOS.size());
   }


    /**
     * 完成课程
     * @param id
     * @return
     */
   @RequestMapping("/easCourse/complete")
   @ResponseBody
   public Result complete(int id){
       Result result=   iCourseService.complete(id);
       return result;
   }


    @RequestMapping("/easCourse/choiceList")
    @ResponseBody
    public LayuiResult  choiceList(CoursePageQueryDTO coursePageQueryDTO,HttpSession session){
        //获取当前登录用户
        UserVO user = (UserVO) session.getAttribute("login_user");
        String username = user.getUsername();
        List<CourseVO> courseVOS= iCourseService.findallCourseByCondition(coursePageQueryDTO,username);
        return LayuiResult.ok(courseVOS,courseVOS.size());
    }


    /**
     * admin --所有课程
     * @param coursePageQueryDTO
     * @param session
     * @return
     */
    @RequestMapping("/easCourse/getCourseList")
    @ResponseBody
    public LayuiResult getCourseList(CoursePageQueryDTO coursePageQueryDTO,HttpSession session){
        List<CourseVO> courseVOS = iCourseService.getCourseList(coursePageQueryDTO,session);
        return LayuiResult.ok(courseVOS,courseVOS.size());
    }

    /**
     * admin=--- 获取新增页
     * @return
     */
    @GetMapping("/easCourse/courseAddForm")
    public  String getAddIndex(){
        return "/system/course/courseAddForm";
    }


    /**
     * admin=--- 获取编辑页
     * @return
     */
    @GetMapping("/easCourse/courseEditForm")
    public  String getEditIndex(){
        return "/system/course/courseEditForm";
    }


    /**
     * 修改课程
     * @param course
     * @return
     */
    @PostMapping("/easCourse/editCourse")
    @ResponseBody
    public Result editCourse(Course course){
        Result result=iCourseService.editCourse(course);
        return result;
    }


    /**
     * 新增课程
     * @param course
     * @return
     */
    @PostMapping("/easCourse/addCourse")
    @ResponseBody
    public Result addCourse(Course course){
        log.info("开始新增课程:{}",course);
        System.out.println(course);
        Result result=iCourseService.addCourse(course);
        return result;
    }


    @PostMapping("/easCourse/batchDeleteCourse")
    @ResponseBody
    public Result batchDeleteCourse(int ids[]){
        Result result=iCourseService.batchDeleteCourse(ids);
        return result;
    }

    @GetMapping("/easCourse/getCourseById")
    @ResponseBody
    public CourseVO getCourseById(int id){
       return   iCourseService.getCourseById(id);
    }

    /**
     * 查询所有的课程
     * @param baseCoursePageQueryDTO
     * @return
     */
    @GetMapping("/easBaseCourse/list")
    @ResponseBody
    public LayuiResult all(BaseCoursePageQueryDTO  baseCoursePageQueryDTO){
        List<BaseCourse> user= iCourseService.findAllCourse(baseCoursePageQueryDTO);
        return LayuiResult.ok(user,user.size());
    }


    /**
     * 查询所有的课程
     * @param
     * @return
     */
    @GetMapping("/easBaseCourse/search")
    @ResponseBody
    public List<BaseCourse> allBase(BaseCoursePageQueryDTO  baseCoursePageQueryDTO){

        List<BaseCourse> baseCourses= iCourseService.findAllCourse(baseCoursePageQueryDTO);
        return baseCourses;
    }

    /**
     * 选课 --------admin
     * @param courseId
     * @return
     */
    @RequestMapping("/easScore/choiceCourse")
    @ResponseBody
    public Result choiceCourse(int courseId, HttpSession session){
        Result result = iCourseService.choiceCourse(courseId,session);
        return result;
    }



    /**
     * 选课
     * @param courseId
     * @return
     */
    @RequestMapping("/easScore/outCourse")
    @ResponseBody
    public Result outCourse(int courseId, HttpSession session){
        Result result = iCourseService.outCourse(courseId,session);
        return result;
    }





    /**
     * 获取新增课程菜单栏
     * @return
     */
    @GetMapping("/easBaseCourse/baseCourseAddForm")
    public String getAddBaseCourseForm(){
        return "/system/baseCourse/baseCourseAddForm";
    }


    /**
     * 新增基本课程
     * @param baseCourse
     * @return
     */
    @PostMapping("/easBaseCourse/addBaseCourse")
    @ResponseBody
    public Result addBaseCourse(BaseCourse baseCourse){
        Result result= iCourseService.addBaseCourse(baseCourse);
        return result;
    }

    /**
     * 删除基础课程
     */

    @RequestMapping("/easBaseCourse/batchDeleteBaseCourse")
    @ResponseBody
    public void deleteBaseCourse(int ids[] ){
        iCourseService.deleteBaseCourseBach(ids);
    }


    /**
     * 根据id 查询课程
     * @param id
     * @return
     */
    @RequestMapping("/easBaseCourse/getBaseCourseView")
    @ResponseBody
    public BaseCourse getBaseCourse(int id){
      BaseCourse baseCourse =   iCourseService.getBaseCourseById(id);
      return baseCourse;
    }

    @PostMapping("/easBaseCourse/editBaseCourse")
    @ResponseBody
    public Result edit(BaseCourse baseCourse){
        Result result =  iCourseService.edit(baseCourse);
        return result;
    }

}

