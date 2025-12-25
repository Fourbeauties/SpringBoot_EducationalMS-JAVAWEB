package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.constant.MessageConstant;
import com.example.demo.mapper.ScoreMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.*;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.pojo.dto.BaseCoursePageQueryDTO;
import com.example.demo.pojo.dto.CoursePageQueryDTO;
import com.example.demo.pojo.vo.CourseVO;
import com.example.demo.pojo.vo.TeacherListVO;
import com.example.demo.pojo.vo.TeacherVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.Result;
import com.example.demo.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private TeacherMapper teacherMapper;


    /**
     * 删除课程信息
     * @param ids
     * @return
     */
    @Override
    public Result batchDeleteCourse(int[] ids) {
        for(int id:ids){
            /**
             * 根据id 删除eas_course 表
             */
            QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
            courseWrapper.eq("id",id);
            courseMapper.delete(courseWrapper);
            /**
             * 还要删除eas_score
             *
             */
            QueryWrapper<Score> scoreWrapper = new QueryWrapper<>();
            scoreWrapper.eq("c_id",id);
            scoreMapper.delete(scoreWrapper);
        }
        return new Result(0,"删除成功",null);
    }

    /**
     * 查询所有的基本课程
     * @param baseCoursePageQueryDTO
     * @return
     */
    @Override
    public List<BaseCourse> findAllCourse(BaseCoursePageQueryDTO baseCoursePageQueryDTO) {

        List<BaseCourse> baseCourseAll=courseMapper.findBaseCourseAll(baseCoursePageQueryDTO);
        return baseCourseAll;

    }

    /**
     * 添加基本课程
     * @param baseCourse
     * @return
     */
    @Override
    public Result addBaseCourse(BaseCourse baseCourse) {
        //常规思路 课程名称不能重复
        if(baseCourse.getCoursename()==null||baseCourse.getCoursename().length()==0||baseCourse.getSynopsis().length()==0){
            return new Result(1,"数据不能为空",null);
        }

        BaseCourse bCourse= courseMapper.findBaseCourseByName(baseCourse.getCoursename());

        if(bCourse!=null){
            return new Result(1, MessageConstant.BASECOURSE_NO,null);
        }

        courseMapper.addBaseCourse(baseCourse);
        return new Result(0,MessageConstant.BASECOURSE_ADD_SUCEESS,null);

    }

    @Override
    public void deleteBaseCourseBach(int[] ids) {

        for(int id:ids){
            //删除ease_base_course
            courseMapper.deleteBaseCourse(id);
            //删除eas_course 里面包含了基本课程的id
            courseMapper.deleteFromCourseByBaseCourseId(id);
        }

    }

    /**
     * 获取基本课程信息
     * @param id
     * @return
     */
    @Override
    public BaseCourse getBaseCourseById(int id) {
        return  courseMapper.getBaseCourseById(id);
    }

    /**
     * 编辑起初课程
     * @param baseCourse
     * @return
     */
    @Override
    public Result edit(BaseCourse baseCourse) {
        if(baseCourse.getCoursename()==null||baseCourse.getCoursename().length()==0||baseCourse.getSynopsis().length()==0){
            return new Result(1,"数据不能为空",null);
        }
        BaseCourse course = courseMapper.findBaseCourseByName(baseCourse.getCoursename());
        if(course==null){//修改后的名称查询不到。表示该修改生效
            courseMapper.editBaseCurse(baseCourse);
            return new Result(0,MessageConstant.EDIT_SUCCESS,null);
        }
        if(course!=null&&course.getId()==baseCourse.getId()){//就是自己呀
            courseMapper.editBaseCurse(baseCourse);
            return new Result(0,MessageConstant.EDIT_SUCCESS,null);
        }
        return new Result(1,MessageConstant.BASECOURSE_IS_REPATRD,null);
    }


    /**
     * 查询所有的课程
     * @param coursePageQueryDTO
     * @return
     */
    @Override
    public List<CourseVO> findallCourseByCondition(CoursePageQueryDTO coursePageQueryDTO,String username) {

        //查询学生信息，如果是管理员自然是查询不了的
        Student student = studentMapper.findByUsername(username);
        if(student!=null){//查询不到
            coursePageQueryDTO.setStudentId(student.getId());
        }
        if(coursePageQueryDTO.getIsAll()==0){//查询一选课程 但是系统默认是1哇

            List<CourseVO> baseCourseAll=courseMapper.findCourseAllSelected(coursePageQueryDTO);
            return baseCourseAll;
        }
        System.out.println(coursePageQueryDTO+"--------------");

        List<CourseVO> baseCourseAll=courseMapper.findCourseAll(coursePageQueryDTO);
        return baseCourseAll;
    }

    /**
     * 学生选课
     * @param id
     * @param session
     * @return
     */
    @Override
    @Transactional
    public Result choiceCourse(int id, HttpSession session) {
        //1选课 首先判断课程的人数是否是满了
        Course course = courseMapper.selectById(id);
        if(course.getChoiceNum()>=course.getStudentNum()){
            return new Result(1,"该课程选课人数已满",null);
        }
        //2未满 首先往eas_score 表中添加一个数据项初始成绩为0
        //2.1 查询当前学生的id
        UserVO user = (UserVO) session.getAttribute("login_user");
        //根据username 查询学生
        Student student=studentMapper.findByUsername(user.getUsername());
        //判断该学生是否选择该课 eas_score 表中查询
        Score score = scoreMapper.findScoreDetailBysId(student.getId(),course.getId());
        if(score!=null){
            return new Result(1,"请不要重复加入，在我的课程中查看",null);
        }
        //往eas_score 表中添加数据
        scoreMapper.addScoreWithStuAndCou(id,student.getId());
        course.setChoiceNum(course.getChoiceNum()+1);
        //更新课程表中的人数
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",course.getId());
        courseMapper.update(course,queryWrapper);
        return new Result(0,"选课成功",null);
    }


    /**
     * 删除选课
     * @param courseId
     * @param session
     * @return
     */
    @Override
    @Transactional
    public Result outCourse(int courseId, HttpSession session) {
        //获取当前登录信息
        //2.1 查询当前学生的id
        UserVO user = (UserVO) session.getAttribute("login_user");
        //根据username 查询学生
        Student student=studentMapper.findByUsername(user.getUsername());
        //1.删除eas_score
        scoreMapper.deleteScoreWithStuAndCou(courseId,student.getId());
        //2.eas_course 已选数亮剑一
        Course course = courseMapper.selectById(courseId);
        course.setChoiceNum(course.getChoiceNum()-1);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",courseId);
        courseMapper.update(course,queryWrapper);
        return new Result(0,"取消选课陈功",null);
    }

    /**
     * 查询所有的课程-----教师
     * @param coursePageQueryDTO
     * @return
     */
    @Override
    public List<CourseVO> geMyCourseList(CoursePageQueryDTO coursePageQueryDTO,HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("login_user");
        String username = user.getUsername();


        //查询教师的课程信息
        Teacher teacher = teacherMapper.findTeaUsername(username);
        if(teacher!=null){//查询不到
            coursePageQueryDTO.setTeacherId(teacher.getId());
        }

        List<CourseVO> baseCourseAll=courseMapper.findCourseAllMy(coursePageQueryDTO);
        return baseCourseAll;
    }

    /**
     * 完成课程
     * @param id
     * @return
     */
    @Override
    public Result complete(int id) {
        //1.如果课程中有人成绩未录入就不能关闭课程
        List<Score> scores = scoreMapper.selectList(null);
        for(Score sc:scores){
            //查找数据库该表中的每一行，看对应的课 的成绩那一栏
            if(sc.getScore()==null&&sc.getCId()==id){
                return new Result(1,"还有学生未上传成绩",null);
            }
        }

//        //2.如果时间未到课程结束时间也不能结束课程
//        Course course = courseMapper.selectById(id);
//        if(LocalDateTime.now().isAfter(ChronoLocalDateTime.from(course.getEndDate()))){
//            return new Result(1,"未到课程指定结束时间",null);
//        }

        courseMapper.complete(id);

        return new Result(0,"操作成功",null);
    }

    /**
     * 查询所有的课程
     * @param coursePageQueryDTO
     * @param session
     * @return
     */
    @Override
    public List<CourseVO> getCourseList(CoursePageQueryDTO coursePageQueryDTO, HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("login_user");

        List<CourseVO> baseCourseAll=courseMapper.findCourseAllByAdmin(coursePageQueryDTO);
        return baseCourseAll;
    }

    /**
     * 管理员新增课程
     * @param course
     * @return
     */
    @Override
    public Result addCourse(Course course) {
        //2.获取的数据不能为空
        if(course==null){
            return new Result(1,"表单不能为空",null);
        }
        if(course.getEndDate()==null||course.getStudentNum()==null||course.getBaseCourseId()==0||course.getTId()==null||
        course.getTestMode()==null||course.getStartDate()==null||course.getClassHour()==null){
            return new Result(1,"表单不能为空",null);
        }
        System.out.println(course.getEndDate()+")___--------------");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(course.getStartDate().compareTo(course.getEndDate())>0){
            return new Result(1,"不可能时光倒流，请检查时间",null);
        }
        course.setChoiceNum(0);
        course.setComplete(0);
        courseMapper.addCourse(course);

        return new Result(0,MessageConstant.ADD_SUCEESS,null);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public CourseVO getCourseById(int id) {
        return courseMapper.findCourseByCid(id);
    }

    /**
     * 更新课程
     * @param course
     * @return
     */
    @Override
    public Result editCourse(Course course) {
        //2.获取的数据不能为空
        if(course==null){
            return new Result(1,"表单不能为空",null);
        }
        if(course.getEndDate()==null||course.getStudentNum()==null||course.getBaseCourseId()==null||course.getTId()==null||
                course.getTestMode()==null||course.getStartDate()==null||course.getClassHour()==null){
            return new Result(1,"表单不能为空",null);
        }
        System.out.println(course.getEndDate()+")___--------------");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(course.getStartDate().compareTo(course.getEndDate())>0){
            return new Result(1,"不可能时光倒流，请检查时间",null);
        }

        //先删除
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",course.getId());
        courseMapper.update(course,queryWrapper);
        return new Result(0,MessageConstant.EDIT_SUCCESS,null);
    }
}
