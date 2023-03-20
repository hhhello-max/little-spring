package com.framework.test.common;

import com.framework.beans.BeansException;
import com.framework.beans.PropertyValues;
import com.framework.beans.PropertyValue;
import com.framework.beans.factory.ConfigurableListableBeanFactory;
import com.framework.beans.factory.config.BeanDefinition;
import com.framework.beans.factory.config.BeanFactoryPostProcessor;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/**
 *BeanFactoryPostProcessor 实例化 Bean 对象之前，修改 BeanDefinition 属性
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        PropertyValue company = propertyValues.getPropertyValues("company");
        propertyValues.addPropertyValue(new PropertyValue("company", company.getValue()+"改为：字节跳动"));
    }

}
