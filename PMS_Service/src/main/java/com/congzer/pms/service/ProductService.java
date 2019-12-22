package com.congzer.pms.service;

import com.congzer.pms.domain.Product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有
     * @return
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    void save(Product product) throws Exception ;
}
