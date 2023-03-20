package com.framework.beans.factory.config;

/**
 * bean引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }

}
