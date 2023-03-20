package com.framework.beans.factory.config;

import com.framework.beans.BeansException;
import com.framework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition属性信息
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，实例化Bean对象之前，
     * 提供修改BeanDefinition属性的机制。
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
