package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.converter.Husband;
import org.junit.Test;

import java.time.LocalDate;

public class ApiTest17 {

    @Test
    public void test_convert(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring17.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("H" + husband);

    }

}
