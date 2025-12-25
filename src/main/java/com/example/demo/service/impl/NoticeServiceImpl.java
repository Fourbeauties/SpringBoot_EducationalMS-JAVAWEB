package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.pojo.dto.NoticePageQueryDTO;
import com.example.demo.pojo.vo.RoleVO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.Result;
import com.example.demo.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 分页查询通知
     * @param
     * @return
     */
    @Override
    public List<Notice> findPage(NoticePageQueryDTO noticePageQueryDTO, HttpSession session) {
        //获取当前登录的用户信息
        UserVO loginUser = (UserVO) session.getAttribute("login_user");
        String roleName = null;
        List<RoleVO> roles = loginUser.getRoles();
        if(roles!=null){
            roleName=roles.get(0).getName();
        }


        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        if(noticePageQueryDTO.getSearchKey()!=null){
            queryWrapper.like("title",noticePageQueryDTO.getSearchKey());
        }
        /**
         * 判断能否查看
         */
        if(roleName!=null){
            if(!roleName.equals("教师")&&!match(roleName)){
                queryWrapper.ne("type",2);
            }
        }
        List<Notice> notices = noticeMapper.selectList(queryWrapper);
        return notices;
    }

    private boolean match(String text){
        String pattern="管理员";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(text);
        while (matcher.find()){
            return true;
        }
        return false;
    }

    /**
     * 根据id查询通知
     * @param id
     * @return
     */
    @Override
    public Notice findById(Long id) {
        Notice notice = noticeMapper.selectById(id);
        return notice;
    }


    /**
     * 新增通知
     * @param notice
     * @return
     */
    @Override
    public Result addNotice(Notice notice) {

        if(notice.getId()!=null){ //查询这个通知是否已经存在
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",notice.getId());
            noticeMapper.delete(queryWrapper);
        }

        notice.setReleasedate(LocalDate.now());
        int insert = noticeMapper.insert(notice);
        return new Result(0,"ok",null);
    }

    /**
     * 删除通知
     * @param ids
     * @return
     */
    @Override
    public Result deleteList(int[] ids) {
        for(int id:ids){
            QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            noticeMapper.delete(queryWrapper);
        }
        return new Result(0,"ok",null);
    }
}
