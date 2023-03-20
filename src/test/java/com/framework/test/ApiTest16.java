package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.test.circulation.Husband;
import com.framework.test.circulation.Wife;

import org.junit.Test;

public class ApiTest16 {

    @Test
    public void test_circulation(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring16.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("H" + husband.queryWife());
        System.out.println("W" + wife.queryHusband());
    }

}
