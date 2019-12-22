package com.congzer.pms.test;

import com.congzer.pms.domain.Product;
import com.congzer.pms.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductServiceTest {

    @Test
    public void testSave() throws Exception {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取service层对象
        ProductService productService = ac.getBean("productService",ProductService.class);
        Product product = new Product();
        product.setProductNum("congzer-100");
        product.setProductName("贵州百年游");
        product.setCityName("贵州");

        productService.save(product);
    }
}
