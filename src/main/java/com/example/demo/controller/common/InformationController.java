package com.example.demo.controller.common;

import com.example.demo.mapper.ScoreMapper;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.vo.EchartsVO;
import com.example.demo.result.Result;
import com.example.demo.service.ICourseService;
import com.example.demo.service.IScoreService;
import com.example.demo.service.IStudentService;
import com.example.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InformationController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IScoreService iScoreService;
    @RequestMapping("/easLogin/easEchart/peopleEchart")
    public String index(){
        return "/echarts/peopleEcharts";
    }

    /**
     *
     * @return
     */
    @GetMapping("/easEchart/getAllStuAndTea")
    @ResponseBody
    public EchartsVO getAllStuAndTea(){


        List<Student> totalStus = studentService.findAllStudents();
        int totalStuN=totalStus.size();
        List<Teacher> totalTeas = teacherService.findAllTeachers();
        int totalTeaN=totalTeas.size();
        EchartsVO echartsVO = new EchartsVO();
        echartsVO.setTotalStuN(totalStuN);
        echartsVO.setTotalTeaN(totalTeaN);
        return echartsVO;

    }

    /**
     *
     * @return
     */
    @GetMapping("/easEchart/getAllSex")
    @ResponseBody
    public EchartsVO getAllSex(){

        EchartsVO echartsVO = new EchartsVO();
        List<Student> totalStuF = studentService.findAllStudentsBySexF();
        int totalWoman=totalStuF.size();
        echartsVO.setTotalWoman(totalWoman);
        List<Student> totaStuM = studentService.findAllStudentsBySexm();
        int totalMan=totaStuM.size();
        echartsVO.setTotalMan(totalMan);
        return  echartsVO;
    }

    @RequestMapping("/easLogin/easEchart/scoreEchart")
    public String scoreIndex(){
        return "/echarts/ScoreEcharts";
    }

    /**
     * 获取学生课程通过信息
     * @param baseCourseId
     * @return
     */
    @GetMapping("/easEchart/getAllClassScore")
    @ResponseBody
    public EchartsVO getAllClassScore(int baseCourseId){
        EchartsVO echartsVO =iScoreService.findAllDetailNum(baseCourseId);
        return echartsVO;
    }
}
