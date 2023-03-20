package com.framework.beans.factory.support;

import com.framework.beans.BeansException;
import com.framework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String name, Constructor constructor, Object[] args) throws BeansException;

}
