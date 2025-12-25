package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.BaseCourse;
import com.example.demo.pojo.Score;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.dto.ScorePageQueryDTO;
import com.example.demo.pojo.dto.ScoreUpdateDTO;
import com.example.demo.pojo.vo.EchartsVO;
import com.example.demo.pojo.vo.ScoreTeaVO;
import com.example.demo.pojo.vo.ScoreVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.Result;
import com.example.demo.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {
    @Autowired
    private ScoreMapper scoreMapper;


    @Autowired
    private StudentMapper studentMapper;
    /**
     * 查询所的成绩
     * @param scorePageQueryDTO
     * @return
     */
    @Override
    public List<ScoreVO> getAllScores(ScorePageQueryDTO scorePageQueryDTO, HttpSession session) {
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        Student student = studentMapper.findByUsername(loginUser.getUsername());
        if(student!=null){
            scorePageQueryDTO.setSid(student.getId());
        }
        


        List<ScoreVO> baseCourseAll=scoreMapper.findScoreAll(scorePageQueryDTO);
        return baseCourseAll;
    }

    /**
     * 查询课程通过信息
     * @param baseCourseId
     * @return
     */
    @Override
    public EchartsVO findAllDetailNum(int baseCourseId) {
        EchartsVO echartsVO = new EchartsVO();
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id",baseCourseId);
        //统计查询两次
        queryWrapper.eq("result","已通过");
        List<Score> pass = scoreMapper.selectList(queryWrapper);
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("result","未通过");
        List<Score> noPass = scoreMapper.selectList(wrapper);
        echartsVO.setTotalPass(pass.size());
        echartsVO.setTotalNoPass(noPass.size());
        return echartsVO;
    }

    /**
     * 查询所有的学生
     * @param scorePageQueryDTO
     * @return
     */
    @Override
    public List<ScoreTeaVO> getAllStudentScores(ScorePageQueryDTO scorePageQueryDTO,Integer tId) {


        List<ScoreTeaVO> scoreTeaVOS=scoreMapper.getAllStudentScores(scorePageQueryDTO,tId);
        return scoreTeaVOS;

    }

    /**
     * 更新成绩单
     * @param scoreUpdateDTOS
     * @return
     */
    @Override
    public Result updateScore(ScoreUpdateDTO scoreUpdateDTOS) {
        if(scoreUpdateDTOS==null){
            return new Result(1,"请输入成绩，以及结果",null);
        }
        if(scoreUpdateDTOS.getScore().equals("")||scoreUpdateDTOS.getScore()==null){
            return new Result(1,"请输入成绩",null);
        }
        if((!scoreUpdateDTOS.getResult().equals("通过"))&&(!scoreUpdateDTOS.getResult().equals("不通过"))){
            return new Result(1,"结果请输入 通过|不通过",null);
        }
        Score oldScore = scoreMapper.selectById(scoreUpdateDTOS.getId());
        oldScore.setScore(Integer.parseInt(scoreUpdateDTOS.getScore()));
        oldScore.setResult(scoreUpdateDTOS.getResult());
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",oldScore.getId());
        scoreMapper.update(oldScore,queryWrapper);
        return new Result(0,"上传成功",null);
    }
}
