package com.framework.test.common;

import com.framework.beans.BeansException;
import com.framework.beans.factory.config.BeanPostProcessor;
import com.framework.test.bean.UserService;

/**
 * BeanPostProcessor 在 Bean 对象执行初始化方法前后进行扩展
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation(userService.getLocation()+"改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
