package com.framework.beans.factory;

/**
 * 用户可以实现一个自己的工厂，创建自己定义的Bean对象
 * @param <T>
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
