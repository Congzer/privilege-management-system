package com.congzer.pms.dao;

import com.congzer.pms.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface ProductDao {

    @Select("select * from product where id = #{id}")
    Product findById(String id) throws  Exception;
    /**
     * 查询所有
     * @return
     */
    @Select("select * from product")
     List<Product> findAll() throws Exception;

    /**
     * 添加产品
     * @param product
     * @throws Exception
     */
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
