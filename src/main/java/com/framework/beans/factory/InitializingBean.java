package com.framework.beans.factory;

/**
 * bean属性填充后使用
 */
public interface InitializingBean {

    /**
     * Bean在前置和后置之间调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
