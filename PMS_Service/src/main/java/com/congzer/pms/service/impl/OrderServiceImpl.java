package com.congzer.pms.service.impl;

import com.congzer.pms.dao.OrderDao;
import com.congzer.pms.domain.Orders;
import com.congzer.pms.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        //在调用da层方法进行查询时加PageHelper插件的方法
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String orderId) throws Exception {
        return orderDao.findById(orderId);
    }
}
