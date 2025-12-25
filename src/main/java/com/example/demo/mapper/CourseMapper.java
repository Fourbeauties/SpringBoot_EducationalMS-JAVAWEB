package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.BaseCourse;
import com.example.demo.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.BaseCoursePageQueryDTO;
import com.example.demo.pojo.dto.CoursePageQueryDTO;
import com.example.demo.pojo.vo.CourseVO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

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
public interface CourseMapper extends BaseMapper<Course> {


    List<BaseCourse> findBaseCourseAll(@Param("baseCoursePageQueryDTO") BaseCoursePageQueryDTO baseCoursePageQueryDTO);

    /**
     * 根据 课程名称查询课程
     * @param coursename
     */
    @Select("select * from eas_base_course where  eas_base_course.coursename=#{coursename}")
    BaseCourse findBaseCourseByName(String coursename);

    /**
     * 新增基础课程
     * @param baseCourse
     */

    void addBaseCourse(BaseCourse baseCourse);

    /**
     * 删除eas_base_course
     * @param id
     */
    @Delete("delete from eas_base_course where id=#{id}")
    void deleteBaseCourse(int id);

    /**
     * 根据baseCourseId 删除eas_Course 中的内容
     * @param id
     */
    @Delete("delete from eas_course where eas_course.base_course_id=#{id}")
    void deleteFromCourseByBaseCourseId(int id);

    /**
     * 根据id获取基本的课程信息
     * @param id
     * @return
     */
    @Select("select * from eas_base_course where id=#{id}")
    BaseCourse getBaseCourseById(int id);

    /**
     * 编辑基础课程信息
     * @param baseCourse
     */
    void editBaseCurse(BaseCourse baseCourse);

    /**
     * 查询所有耳朵课程信息
     * @param page
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> findCourseAll(@Param("coursePageQueryDTO") CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 查询一选课程
     * @param page
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> findCourseAllSelected(@Param("coursePageQueryDTO") CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 查询教师课程
     * @param page
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> findCourseAllMy(@Param("coursePageQueryDTO") CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 更新课程
     * @param id
     */
    @Update("update eas_course set complete=1 where eas_course.id=#{id}")
    void complete(int id);

    /**
     * admin 查询全部课程信息
     * @param page
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> findCourseAllByAdmin( @Param("coursePageQueryDTO") CoursePageQueryDTO coursePageQueryDTO);

    /**
     * 新增基础课程
     * @param course
     */
    void addCourse(Course course);

    /**
     * 根据课程id 查询
     * @param id
     */
    CourseVO findCourseByCid(int id);
}
