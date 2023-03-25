package com.framework.mybatis;

import cn.hutool.core.lang.ClassScanner;
import com.framework.beans.BeansException;
import com.framework.beans.PropertyValue;
import com.framework.beans.PropertyValues;
import com.framework.beans.factory.ConfigurableListableBeanFactory;
import com.framework.beans.factory.config.BeanDefinition;
import com.framework.beans.factory.support.BeanDefinitionRegistry;
import com.framework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import com.framework.mybatis.support.SqlSessionFactory;

import java.util.Set;

public class MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Set<Class<?>> classes = ClassScanner.scanPackage(basePackage);
            for (Class<?> clazz : classes){
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                PropertyValues propertyValues = new PropertyValues();
                propertyValues.addPropertyValue(new PropertyValue("mapperInterface", clazz));
                propertyValues.addPropertyValue(new PropertyValue("sqlSessionFactory", sqlSessionFactory));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setBeanClass(MapperFactoryBean.class);
                registry.registryBeanDefinition(clazz.getSimpleName(), beanDefinition);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
