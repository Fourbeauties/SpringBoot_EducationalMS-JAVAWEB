package com.example.demo.service;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.TeacherPageQueryDTO;
import com.example.demo.pojo.vo.TeacherListVO;
import com.example.demo.pojo.vo.TeacherVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
public interface ITeacherService extends IService<Teacher> {

    /**
     * 分页查询老师
     * @param teacherPageQueryDTO
     * @return
     */
    List<TeacherListVO> findAllTeacher(TeacherPageQueryDTO teacherPageQueryDTO);

    /**
     * 查询所有的教师
     * @return
     */

    List<Teacher> findAllTeachers();


    Teacher findAllTeachersByUsername(String username);
}
