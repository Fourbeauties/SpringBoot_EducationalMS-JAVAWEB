package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.dto.NoticePageQueryDTO;
import com.example.demo.result.Result;

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
public interface INoticeService extends IService<Notice> {
    /**
     * 分页查询通知

     * @return
     */
    List<Notice> findPage(NoticePageQueryDTO noticePageQueryDTO, HttpSession session);

    /**
     * 根据id查询 通知
     * @param id
     * @return
     */
    Notice findById(Long id);

    /**
     * 新增通知
     * @param notice
     * @return
     */
    Result addNotice(Notice notice);

    /**
     * 删除通知
     * @param ids
     * @return
     */
    Result deleteList(int[] ids);
}
