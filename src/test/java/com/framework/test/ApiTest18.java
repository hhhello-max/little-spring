package com.framework.test;

import com.framework.context.support.ClassPathXmlApplicationContext;
import com.framework.jdbc.core.JdbcTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ApiTest18 {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:jdbc.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    @Test
    public void execute(){
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (1, '184172133','01_50', now(), now())");
    }

    @Test
    public void queryForListTest() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user");
        for (Map<String, Object> objectMap : allResult) {
            System.out.println("测试结果：" + objectMap);
        }
    }

}
