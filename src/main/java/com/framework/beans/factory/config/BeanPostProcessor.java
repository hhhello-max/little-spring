package com.framework.beans.factory.config;

import com.framework.beans.BeansException;

/**
 * 修改新实例化Bean对象的扩展点
 */
public interface BeanPostProcessor {

    /**
     * 在bean对象执行初始化之前，执行此方法。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean对象执行初始化之后，执行此方法。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
