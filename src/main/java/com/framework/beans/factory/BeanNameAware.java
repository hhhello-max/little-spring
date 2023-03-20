package com.framework.beans.factory;

/**
 * 实现此接口可以感知自己的beanName
 */
public interface BeanNameAware {

    void setBeanName(String name);

}
