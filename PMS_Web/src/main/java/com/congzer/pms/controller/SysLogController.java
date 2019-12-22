package com.congzer.pms.controller;

import com.congzer.pms.domain.SysLog;
import com.congzer.pms.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        mav.addObject("sysLogs",sysLogs);
        mav.setViewName("syslog_list");
        return mav;
    }
}
