package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.StudentPageQueryDTO;
import com.example.demo.pojo.vo.StudentListVO;
import com.example.demo.pojo.vo.StudentVO;
import org.apache.ibatis.annotations.Param;
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
public interface IStudentService extends IService<Student> {

    /**
     * 查询所有的学生
     * @return
     */
    List<StudentListVO> findAllStudent(@Param("studentPageQueryDTO") StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 查询所有的学生
     * @return
     */
    List<Student> findAllStudents();

    /**
     * 查询所有的女生
     * @return
     */
    List<Student> findAllStudentsBySexF();

    /**
     * 查询所有的男生
     * @return
     */
    List<Student> findAllStudentsBySexm();
}
