package com.congzer.pms.controller;

import com.congzer.pms.domain.Permission;
import com.congzer.pms.domain.Role;
import com.congzer.pms.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mav.addObject("roleList",roles);
        mav.setViewName("role_list");
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findPermissionsByRoleId.do")
    public ModelAndView findPermissionsByRoleId(@RequestParam(name = "id") String id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(id);
        mav.addObject("role",role);
        List<Permission> permissionList = roleService.findPermissionsByRoleId(id);
        mav.addObject("permissionList",permissionList);
        mav.setViewName("role_permission_add");
        return mav;
    }

    @RequestMapping("/addPermission.do")
    public String addPermission(@RequestParam("roleId") String roleId,@RequestParam("ids")String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleService.addPermission(permissionId,roleId);
        }
        return "redirect:findAll.do";
    }
}
