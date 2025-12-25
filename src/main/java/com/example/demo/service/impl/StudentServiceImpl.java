package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.StudentPageQueryDTO;
import com.example.demo.pojo.vo.StudentListVO;
import com.example.demo.pojo.vo.StudentVO;
import com.example.demo.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    /**
     * 查询所有的学生
     * @param studentPageQueryDTO
     * @return
     */
    @Override
    public List<StudentListVO> findAllStudent(StudentPageQueryDTO studentPageQueryDTO) {


        List<StudentVO> studentVOS=studentMapper.findStusByCondition(studentPageQueryDTO);

        List<StudentListVO> studentList = new ArrayList();
        for (StudentVO s:studentVOS){
            StudentListVO studentListVO = new StudentListVO();
            User user=new User();

            BeanUtils.copyProperties(s,user);
            BeanUtils.copyProperties(s,studentListVO);

            studentListVO.setUser(user);
            studentList.add(studentListVO);
        }
        return studentList;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }

    @Override
    public List<Student> findAllStudentsBySexF() {
        return studentMapper.findAllSexF();
    }

    @Override
    public List<Student> findAllStudentsBySexm() {
        return studentMapper.findAllSexM();
    }
}
