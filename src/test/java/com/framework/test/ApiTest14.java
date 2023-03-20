package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.bean2.IProductService;
import org.junit.Test;

public class ApiTest14 {

    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IProductService service = applicationContext.getBean("productService", IProductService.class);
        System.out.println(service.queryProductInfo());
    }

}
