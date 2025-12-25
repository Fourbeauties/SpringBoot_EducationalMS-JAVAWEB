package com.example.demo.service;

import com.example.demo.pojo.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.ScorePageQueryDTO;
import com.example.demo.pojo.dto.ScoreUpdateDTO;
import com.example.demo.pojo.vo.EchartsVO;
import com.example.demo.pojo.vo.ScoreTeaVO;
import com.example.demo.pojo.vo.ScoreVO;
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
public interface IScoreService extends IService<Score> {

    /**
     * 分页查询成绩
     * @param scorePageQueryDTO
     * @return
     */
    List<ScoreVO> getAllScores(ScorePageQueryDTO scorePageQueryDTO, HttpSession session);

    /**
     * 查询课程通过信息
     * @param baseCourseId
     * @return
     */
    EchartsVO findAllDetailNum(int baseCourseId);

    /**
     *
     * @param scorePageQueryDTO
     * @return
     */
    List<ScoreTeaVO> getAllStudentScores(ScorePageQueryDTO scorePageQueryDTO,Integer tId);

    /**
     * 跟新成绩单
     * @param scoreUpdateDTOS
     * @return
     */
    Result updateScore(ScoreUpdateDTO scoreUpdateDTOS);
}
