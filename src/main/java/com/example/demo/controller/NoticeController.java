package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Notice;
import com.example.demo.pojo.dto.NoticePageQueryDTO;
import com.example.demo.result.Result;
import com.example.demo.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.rmi.MarshalledObject;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Controller
@RequestMapping
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;

    /**
     * 获取通知
     * @return
     */
    @GetMapping("/main/getNotice")
    @ResponseBody
    public Result getNoteAll(NoticePageQueryDTO noticePageQueryDTO, HttpSession session){
        List<Notice> noticePage =iNoticeService.findPage(noticePageQueryDTO,session);
        return new Result(0,"ok",noticePage);
    }

    /**
     * 查询通知
     * @param id
     * @return
     */
    @RequestMapping("/main/lookNotice")
//    @ResponseBody
    public ModelAndView  getNoteById(Long id){
        ModelAndView modelAndView = new ModelAndView();
        Notice noticeList= iNoticeService.findById(id);
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/homeNotice");
        return modelAndView;
    }


    /**
     * 查询通知
     * @param id
     * @return
     */
    @RequestMapping("/easNotice/look")
//    @ResponseBody
    public ModelAndView  getNoteByIdAdmin(Long id){
        ModelAndView modelAndView = new ModelAndView();
        Notice noticeList= iNoticeService.findById(id);
        modelAndView.addObject("noticeList",noticeList);
        modelAndView.setViewName("/notice");
        return modelAndView;
    }


    /**
     * 跳转至我的成绩
     * @return
     */
    @RequestMapping("/easLogin/easNotice/index")
    public String index(){
        return "/system/notice/adminNoticeList";
    }


    @GetMapping("/easNotice/list")
    @ResponseBody
    public Result list(NoticePageQueryDTO noticePageQueryDTO,HttpSession session){
        List<Notice> noticePage =iNoticeService.findPage(noticePageQueryDTO,session);
        return new Result(0,"ok",noticePage);
    }

    @GetMapping("/easNotice/addPage")
    public String addNotePage(){
        return "/system/notice/noticeAdd";
    }


    /**
     * 新增通知
     * @param notice
     * @return
     */
    @PostMapping("/easNotice/addNotice")
    @ResponseBody
    public Result addNotice(Notice notice){
        Result  result=   iNoticeService.addNotice(notice);
        return result;
    }

    /**
     * 删除通知
     * @param ids
     * @return
     */
    @GetMapping("/easNotice/deleteList")
    @ResponseBody
    public Result deleteNotice(int ids[]){
        Result result=    iNoticeService.deleteList(ids);
        return result;
    }


}

