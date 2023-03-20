package com.framework.beans.factory.config;

public interface SingletonBeanRegistry {

    /**
     * 获取单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例对象
     * @param beanName
     * @param singletonObject
     */
    void registrySingleton(String beanName, Object singletonObject);

    void destroySingletons();

}
