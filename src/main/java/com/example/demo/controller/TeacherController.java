package com.example.demo.controller;


import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.dto.StudentPageQueryDTO;
import com.example.demo.pojo.dto.TeacherPageQueryDTO;
import com.example.demo.pojo.vo.StudentListVO;
import com.example.demo.pojo.vo.TeacherListVO;
import com.example.demo.pojo.vo.TeacherVO;
import com.example.demo.result.LayuiResult;
import com.example.demo.result.Result;
import com.example.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping("easLogin/easTeacher/index")
    public String index(){
        return "/system/teacher/index";
    }



    @GetMapping("/easTeacher/list")
    @ResponseBody
    public LayuiResult all(TeacherPageQueryDTO teacherPageQueryDTO){
        List<TeacherListVO> user= iTeacherService.findAllTeacher(teacherPageQueryDTO);
        return LayuiResult.ok(user,user.size());
    }

    @GetMapping("/easTeacher/search")
    @ResponseBody
    public List<Teacher> getAllTeachers(){
        List<Teacher> list = iTeacherService.list();
        return list;
    }

}

