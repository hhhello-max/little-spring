package com.framework.mybatis;

import com.framework.beans.factory.FactoryBean;
import com.framework.beans.factory.InitializingBean;
import com.framework.core.resource.DefaultResourceLoader;
import com.framework.core.resource.Resource;
import com.framework.mybatis.support.SqlSessionFactory;
import com.framework.mybatis.support.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String resource;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(this.resource);
        try(InputStream inputStream = resource.getInputStream()) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
