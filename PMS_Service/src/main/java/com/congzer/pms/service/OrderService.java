package com.congzer.pms.service;

import com.congzer.pms.domain.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> findAll(int page,int size) throws  Exception;

    Orders findById(String orderId) throws  Exception;
}
