package com.framework.jdbc.support;

import cn.hutool.core.lang.Assert;
import com.framework.beans.factory.InitializingBean;

import javax.sql.DataSource;

public abstract class JdbcAccessor implements InitializingBean {

    private DataSource dataSource;

    public DataSource getDataSource() {
        Assert.state(dataSource!=null, "No datasource set!");
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (getDataSource() == null){
            throw new IllegalArgumentException("No datasource available.");
        }
    }
}
