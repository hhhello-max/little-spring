package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.pointcutTest.IBookService;
import org.junit.Test;

public class ApiTest15 {

    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring12.xml");
        IBookService bookService = applicationContext.getBean("bookService", IBookService.class);
        bookService.bookmark("学会提问");
    }

}
