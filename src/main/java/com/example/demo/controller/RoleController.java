package com.example.demo.controller;


import com.example.demo.pojo.Permission;
import com.example.demo.pojo.Role;
import com.example.demo.result.Result;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping()
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IPermissionService iPermissionService;
    /**
     * 跳转至我的成绩
     * @return
     */
    @RequestMapping("/easLogin/easRole/index")
    public String index(){
        return "/system/role/index";
    }


    /**
     * 查询所以的角色
     * @return
     */
    @GetMapping("/easRole/search")
    @ResponseBody
    public List<Role> roleSeache(){
        return iRoleService.findAll();
    }

    @GetMapping("/easRole/list")
    @ResponseBody
    public Result list(){
        return new Result(0,"ok",iRoleService.findAll());
    }


    /**
     *
     * @return
     */
    @RequestMapping("/easRole/roleForm")
    public String indexRole(){
        return "/system/role/roleForm";
    }


    /**
     *
     * @return
     */
    @RequestMapping("/easRole/addRole")
    @ResponseBody
    public Result addRole(Role role){
        Result result= iRoleService.addRole(role);
        return result;
    }


    /**
     * 获取对应的权限
     * @param id
     * @return
     */
    @PostMapping("/easRole/rolePers")
    @ResponseBody
    public List<Integer> rolePers(int id){
        List<Permission> list=   iPermissionService.findPersByRoleId(id);
        ArrayList<Integer> list1 = new ArrayList<>();
        for (Permission li:list){
            list1.add(li.getId());
        }
        return  list1;
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @PostMapping("/easRole/batchDeleteRole")
    @ResponseBody
    public Result batchDeleteRole(int ids[]){
       return   iRoleService.batchDeleteRole(ids);
    }

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    @GetMapping("/easRole/getRoleView")
    @ResponseBody
    public Role getRoleView(int id){
        return iRoleService.getById(id);
    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @RequestMapping("/easRole/editRole")
    @ResponseBody
    public Result editRole(Role role){
      return    iRoleService.editRole(role);
    }

    /**
     * 角色授权
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/easRole/assignPers")
    @ResponseBody
    public Result assignPers(int roleId,String ids){
        int[] id = new int[10];
        ArrayList<Integer> list = new ArrayList<>();
        if(ids.length()!=0){
            String[] split = ids.split(",");
            int i=0;
            for(String s:split){
                list.add(Integer.parseInt(s));
                id[i]=Integer.parseInt(s);
                i++;
            }
        }
        return iRoleService.assignPers(roleId,list);
    }
}

