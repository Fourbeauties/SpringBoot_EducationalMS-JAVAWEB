package com.example.demo.controller;


import com.example.demo.pojo.Student;
import com.example.demo.pojo.dto.StudentPageQueryDTO;
import com.example.demo.pojo.vo.StudentListVO;
import com.example.demo.pojo.vo.StudentVO;
import com.example.demo.result.LayuiResult;
import com.example.demo.result.Result;
import com.example.demo.service.IStudentService;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping("/easLogin/easStudent/index")
    public String index(){
        return "/system/student/index";
    }

    @GetMapping("/easStudent/list")
    @ResponseBody
    public LayuiResult all(StudentPageQueryDTO studentPageQueryDTO){
      List<StudentListVO>  user= iStudentService.findAllStudent(studentPageQueryDTO);
      return LayuiResult.ok(user,user.size());
    }

}

