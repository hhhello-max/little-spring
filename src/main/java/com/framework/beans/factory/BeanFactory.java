package com.framework.beans.factory;

import com.framework.beans.BeansException;

/**
 * bean工厂: 获取bean实例
 */
public interface BeanFactory {

    /**
     * 返回Bean的实例对象
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
