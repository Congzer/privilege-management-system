package com.congzer.pms.controller;

import com.congzer.pms.domain.Role;
import com.congzer.pms.domain.UserInfo;
import com.congzer.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mav.addObject("userList",users);
        mav.setViewName("user_list");
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mav.addObject("user",userInfo);
        mav.setViewName("user_detail1");
        return mav;
    }

    @RequestMapping("/findRolesByUserId.do")
    public ModelAndView findRolesByUserId(String id) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Role> roles = userService.findRolesByUserId(id);
        UserInfo userInfo = userService.findById(id);
        System.out.println(userInfo.toString());
        mav.addObject("user",userInfo);
        mav.addObject("roleList",roles);
        //查询出用户没有的角色后，跳转角色添加页面，并在页面读取响应的数据
        mav.setViewName("user_role_add");
        return mav;
    }

    @RequestMapping("/addRole.do")
    public String addRole(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids")String[] ids) throws Exception{
        //System.out.println(ids.toString());
        for (String roleId : ids) {
            userService.saveRole(userId,roleId);
        }
        return "redirect:findAll.do";
    }

}
