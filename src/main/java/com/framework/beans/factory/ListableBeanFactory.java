package com.framework.beans.factory;

import com.framework.beans.BeansException;

import java.util.Map;

/**
 * 主要获取同类型的Bean对象
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回Bean实例
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();

}
