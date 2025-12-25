package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.TeacherPageQueryDTO;
import com.example.demo.pojo.vo.StudentVO;
import com.example.demo.pojo.vo.TeacherVO;
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
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 根据id 查询教师
     * @param id
     * @return
     */
    TeacherVO findTeaById(Integer id);

    List<TeacherVO> findTeasByCondition(@Param("teacherPageQueryDTO") TeacherPageQueryDTO teacherPageQueryDTO);

    /**
     * 查询所有的教师
     * @return
     */
    @Select("select * from eas_teacher ")
    List<Teacher> findAllTeachers();

    /**
     * 根据用户名查询教师
     * @param username
     * @return
     */
    @Select("select * from eas_teacher where eas_teacher.username=#{username}")
    Teacher findTeaUsername(String username);
}
