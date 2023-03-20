package com.framework.test.tx;

import com.framework.jdbc.core.JdbcTemplate;
import com.framework.transaction.annotation.Transactional;

public class JdbcService {

    /**
     * 使用注解事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveData(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (11, '184172134','01_50', now(), now())");
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (11, '184172134','01_50', now(), now())");
    }

    public void saveDataNoTransaction(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (10, '184172135','01_50', now(), now())");
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (10, '184172135','01_50', now(), now())");
    }

}
