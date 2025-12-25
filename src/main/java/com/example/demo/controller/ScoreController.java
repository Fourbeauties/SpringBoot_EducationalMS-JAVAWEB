package com.example.demo.controller;


import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.dto.ScorePageQueryDTO;
import com.example.demo.pojo.dto.ScoreUpdateDTO;
import com.example.demo.pojo.vo.RoleVO;
import com.example.demo.pojo.vo.ScoreTeaVO;
import com.example.demo.pojo.vo.ScoreVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.LayuiResult;
import com.example.demo.result.Result;
import com.example.demo.service.IScoreService;
import com.example.demo.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ScoreController {

    private final String TEACHER_NAME="教师";

    @Autowired
    private IScoreService iScoreService;

    @Autowired
    private ITeacherService teacherService;


    /**
     * 跳转至我的成绩
     * @return
     */
    @RequestMapping("/easLogin/easScore/myScoreIndex")
    public String myScore(){
        return "/system/score/myScoreIndex";
    }



    /**
     * 跳转至
     * @return
     */
    @RequestMapping("/easLogin/easScore/scoreIndex")
    public String coreListt(){
        return "/system/score/studentScoreIndex";
    }


    /**
     * 跳转至
     * @return
     */
    @RequestMapping("/easLogin/easScore/myStudentIndex")
    public String myStudentIndex(){
        return "/system/score/myStudentIndex";
    }


    /**
     * 查询我的成绩
     * @param scorePageQueryDTO
     * @param session
     * @return
     */
    @RequestMapping("/easScore/myScore")
    @ResponseBody
    public LayuiResult getAllScore(ScorePageQueryDTO scorePageQueryDTO, HttpSession session){

        List<ScoreVO> scoreVOList = iScoreService.getAllScores(scorePageQueryDTO,session);

        return LayuiResult.ok(scoreVOList,scoreVOList.size());
    }



    @RequestMapping("easScore/stuScoreList")
    @ResponseBody
    public LayuiResult stuScoreList(ScorePageQueryDTO scorePageQueryDTO, HttpSession session, ModelAndView modelAndView){
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        List<RoleVO> roles = loginUser.getRoles();
        String roleName=null;
        if(roles!=null){
            roleName=roles.get(0).getName();
        }
        if(!roleName.equals(TEACHER_NAME)){
          return  LayuiResult.ok("无权查看",0);
        }
        String username = loginUser.getUsername();
        Teacher teacher = teacherService.findAllTeachersByUsername(username);
        System.out.println(teacher+"-------1");
        List<ScoreTeaVO> scoreVOList = iScoreService.getAllStudentScores(scorePageQueryDTO,teacher.getId());
        for (ScoreTeaVO scoreTeaVO : scoreVOList) {
            System.out.println(scoreTeaVO+"---------------1");
        }
        return LayuiResult.ok(scoreVOList,scoreVOList.size());
    }


    @RequestMapping("/easScore/updateScore")
    @ResponseBody
    public Result updateScore( ScoreUpdateDTO scoreUpdateDTOS){
        return     iScoreService.updateScore(scoreUpdateDTOS);

    }

}

