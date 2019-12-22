package com.congzer.pms.controller;

import com.congzer.pms.domain.Orders;
import com.congzer.pms.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //未分页
    /*@RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Orders> orders = orderService.findAll();
        //将查询结果存入request域
        mav.addObject("ordersList",orders);
        //设置跳转的页面
        mav.setViewName("orders_list");
        return mav;
    }*/

    //分页展示
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "4") Integer size) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Orders> orders = orderService.findAll(page,size);
        //创建分页Bean对象
        PageInfo pageInfo = new PageInfo(orders);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders_page_list");
        return mav;
    }

    //查询具体某一个订单的详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String orderId) throws Exception {
        ModelAndView mav = new ModelAndView();
        Orders order = orderService.findById(orderId);
        mav.addObject("orders",order);
        mav.setViewName("order_detail");
        return mav;
    }
}
