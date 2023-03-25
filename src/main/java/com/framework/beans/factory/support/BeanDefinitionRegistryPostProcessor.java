package com.framework.beans.factory.support;

import com.framework.beans.BeansException;
import com.framework.beans.factory.config.BeanFactoryPostProcessor;
import com.framework.beans.factory.config.BeanPostProcessor;

/**
 * 允许外部注册对象的接口
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)throws BeansException;

}
