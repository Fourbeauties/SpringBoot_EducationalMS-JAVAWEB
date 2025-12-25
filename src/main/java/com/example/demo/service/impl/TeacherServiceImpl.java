package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Teacher;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.dto.TeacherPageQueryDTO;
import com.example.demo.pojo.vo.TeacherListVO;
import com.example.demo.pojo.vo.TeacherVO;
import com.example.demo.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    //分页查询老师
    @Override
    public List<TeacherListVO> findAllTeacher(TeacherPageQueryDTO teacherPageQueryDTO) {


        List<TeacherVO> teacherVOS=teacherMapper.findTeasByCondition(teacherPageQueryDTO);
        List<TeacherListVO> teacherListVO = new ArrayList();
        for (TeacherVO s:teacherVOS){
            TeacherListVO listVO = new TeacherListVO();
            User user=new User();
            BeanUtils.copyProperties(s,user);
            BeanUtils.copyProperties(s,listVO);
            listVO.setUser(user);
            teacherListVO.add(listVO);
        }
        return teacherListVO;
    }

    /**
     * 查询所有的教师
     * @return
     */
    @Override
    public List<Teacher> findAllTeachers() {
        return teacherMapper.findAllTeachers();
    }

    @Override
    public Teacher findAllTeachersByUsername(String username) {
        return teacherMapper.findTeaUsername(username);
    }
}
