package com.example.demo.controller.common;


import com.example.demo.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {


    @RequestMapping("/study/login")
    public String index(){
        return "login";
    }

    /**
     * 退出登录
     * @param
     * @return
     */
    @RequestMapping("/easUser/logout")
    public String logout(HttpSession session){
        session.removeAttribute("login_user");
        session.removeAttribute("users_per");
        return "login";
    }



}
