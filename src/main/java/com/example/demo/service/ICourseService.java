package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.BaseCourse;
import com.example.demo.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.BaseCoursePageQueryDTO;
import com.example.demo.pojo.dto.CoursePageQueryDTO;
import com.example.demo.pojo.vo.CourseVO;
import com.example.demo.pojo.vo.TeacherListVO;
import com.example.demo.result.Result;
import org.apache.ibatis.annotations.Insert;

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
public interface ICourseService extends IService<Course> {

    /**
     * 删除课程信息
     * @param ids
     * @return
     */
     Result batchDeleteCourse(int[] ids);

    /**
     * 查询所有基本课程
     * @param baseCoursePageQueryDTO
     * @return
     */
    List<BaseCourse> findAllCourse(BaseCoursePageQueryDTO baseCoursePageQueryDTO);


    /**
     * 添加基本课程
     * @param baseCourse
     * @return
     */
    Result addBaseCourse(BaseCourse baseCourse);


    /**
     * 删除基础课程
     * @param a
     */
    void deleteBaseCourseBach(int[] a);

    BaseCourse getBaseCourseById(int id);

    /**
     * 编辑基础课程
     * @param baseCourse
     * @return
     */
    Result edit(BaseCourse baseCourse);

    /**
     * 查找所有的课程
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> findallCourseByCondition(CoursePageQueryDTO coursePageQueryDTO,String username);

    /**
     * 选课
     * @param id
     * @return
     */
    Result choiceCourse(int id, HttpSession session);

    /**
     * 删除选课
     * @param courseId
     * @param session
     * @return
     */
    Result outCourse(int courseId, HttpSession session);

    /**
     * 查询所有教师课程
     * @param coursePageQueryDTO
     * @return
     */
    List<CourseVO> geMyCourseList(CoursePageQueryDTO coursePageQueryDTO,HttpSession session);

    /**
     * 完成课程
     * @param id
     * @return
     */
    Result complete(int id);

    /**
     * 查询所有的课程
     * @param coursePageQueryDTO
     * @param session
     * @return
     */
    List<CourseVO> getCourseList(CoursePageQueryDTO coursePageQueryDTO, HttpSession session);

    /**
     * 新增课程
     * @param course
     * @return
     */
    Result addCourse(Course course);

    /**
     * 根据id 获取课程信息
     * @param id
     * @return
     */
    CourseVO getCourseById(int id);

    /**
     * 更新课程
     * @param course
     * @return
     */
    Result editCourse(Course course);
}
