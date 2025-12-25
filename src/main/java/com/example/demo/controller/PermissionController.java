package com.example.demo.controller;


import com.example.demo.pojo.Permission;
import com.example.demo.result.Result;
import com.example.demo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    /**
     *
     * @return
     */
    @RequestMapping("/easLogin/easPermission/index")
    public String index(){
        return "/system/permission/index";
    }

    /**
     *
     * @return
     */
    @GetMapping("/easPermission/parentList")
    @ResponseBody
    public List<Permission> parentList(){
        List<Permission> list = iPermissionService.list();
        List<Permission> list1=new ArrayList<>();
        for(Permission p:list){
            if(p.getParentid()==null){
                list1.add(p);
            }
        }
        return list1;
    }


    @GetMapping("/easPermission/list")
    @ResponseBody
    public Result list(){
        return  new Result(0,"ok",iPermissionService.list());
    }

}

