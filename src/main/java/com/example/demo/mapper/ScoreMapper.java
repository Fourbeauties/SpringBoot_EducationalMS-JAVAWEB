package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.ScorePageQueryDTO;
import com.example.demo.pojo.vo.ScoreTeaVO;
import com.example.demo.pojo.vo.ScoreVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

    /**
     * 删除选课
     * @param courseId
     * @param
     */

    void deleteScoreWithStuAndCou( @Param("cid") int courseId, @Param("sid") int sid);

    /**
     * 新增成绩信息
     * @param id
     * @param id1
     */
    void addScoreWithStuAndCou( @Param("cid") int  id, @Param("sid") Integer id1);

    /**
     * 根据学生id查询成绩表
     * @param
     * @return
     *
     */
    @Select("select * from eas_score where eas_score.s_id=#{sid} and  eas_score.c_id=#{cid}")
    Score findScoreDetailBysId(@Param("sid") Integer sid,@Param("cid") Integer cid);

    /**
     * 查询所有的成绩
     * @param page
     * @param scorePageQueryDTO
     * @return
     */
    List<ScoreVO> findScoreAll(@Param("scorePageQueryDTO") ScorePageQueryDTO scorePageQueryDTO);

    /**
     * 老师查询所有的成绩
     * @param page
     * @param scorePageQueryDTO
     * @return
     */
    List<ScoreTeaVO> getAllStudentScores(@Param("scorePageQueryDTO") ScorePageQueryDTO scorePageQueryDTO,@Param("tId")Integer tId);
}
