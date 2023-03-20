package com.framework.context.support;

import com.framework.beans.BeansException;
import com.framework.beans.factory.config.BeanPostProcessor;
import com.framework.context.ApplicationContext;
import com.framework.context.ApplicationContextAware;

/**
 * 通过BeanPostProcessor实现类感知应用上下文对象
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
