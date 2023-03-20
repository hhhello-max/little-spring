package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.pointcutTest.BookService;
import com.framework.test.pointcutTest.IBookService;
import org.junit.Test;

public class ApiTest12 {

    @Test
    public void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring12.xml");
        IBookService bookService = applicationContext.getBean("bookService", BookService.class);
        bookService.bookmark("7890");
    }

}
