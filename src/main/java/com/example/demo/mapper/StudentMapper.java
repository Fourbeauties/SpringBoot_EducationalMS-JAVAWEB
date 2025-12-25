package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.dto.StudentPageQueryDTO;
import com.example.demo.pojo.vo.StudentVO;
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
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * 根据id 查询学生
     * @param id
     * @return
     */
    Student findStuByID(Integer id);

    /**
     * 查询所有的学生
     * @param studentPageQueryDTO
     * @return
     */
    List<StudentVO> findStusByCondition( @Param("studentPageQueryDTO") StudentPageQueryDTO studentPageQueryDTO);



    /**
     * 根据用户名查询学生
     * @param username
     */
    @Select("select * from eas_student where eas_student.username=#{username}")
    Student findByUsername(String username);

    /**
     * 查询所有的学生
     * @return
     */
    @Select("select * from eas_student")
    List<Student> findAllStudents();

    /**
     * 找男生
     * @return
     */
    @Select("SELECT * FROM eas_student  es where es.sex='女' ")
    List<Student> findAllSexF();

    /**
     * 找女生
     * @return
     */
    @Select("SELECT * FROM eas_student  es where es.sex='男' ")
    List<Student> findAllSexM();

    /**
     * 查找学生 id
     * @param id
     * @return
     */
    StudentVO findStuVOById(Integer id);
}
