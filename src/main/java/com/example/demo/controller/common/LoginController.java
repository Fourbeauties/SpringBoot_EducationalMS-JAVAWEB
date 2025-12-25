package com.example.demo.controller.common;




import com.example.demo.constant.MessageConstant;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.handler.GlobalExceptionHandler;
import com.example.demo.pojo.dto.LoginDTO;
import com.example.demo.pojo.vo.UserVO;
import com.example.demo.result.Result;
import com.example.demo.service.INoticeService;
import com.example.demo.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@ControllerAdvice
@RequestMapping("/easLogin")
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private INoticeService iNoticeService;
    /**
     * 用户登录
     * @param loginDTO
     * @param session
     * @return
     */

    @RequestMapping(path="/login",method=RequestMethod.POST)
    @ResponseBody
    public Result login(LoginDTO loginDTO, HttpSession session ) {

        String validateCode = (String) session.getAttribute("validateCode");
        if (!validateCode.equals(loginDTO.getRandomCode())) {
            return Result.error(MessageConstant.CHECK_CODE_UNRIGHT);
        }

        Result result = iUserService.login(loginDTO,session);
        return result;
    }
    /**
     * 主页
     * @return
     */
    @GetMapping("/main/homePage")
    public String honePage(){
        return "homePage";
    }

    /**
     * 跳转页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String loginSuccess(@PathVariable String page){
        return page;
    }





}
