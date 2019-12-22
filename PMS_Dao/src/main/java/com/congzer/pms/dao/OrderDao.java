package com.congzer.pms.dao;

import com.congzer.pms.domain.Member;
import com.congzer.pms.domain.Orders;
import com.congzer.pms.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {



    @Select("select * from orders")
    @Results({
        @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.congzer.pms.dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{orderId}")
    @Results({
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.congzer.pms.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.congzer.pms.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.congzer.pms.dao.TravellerDao.findByOrderId"))
    })
    Orders findById(String orderId) throws Exception;
}
