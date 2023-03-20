package com.framework.beans.factory.support;

import com.framework.beans.BeansException;
import com.framework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectFromFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return object != NULL_OBJECT? object:null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName){
        if (factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null){
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object!=null? object:NULL_OBJECT));
            }
            return object!=null? object:NULL_OBJECT;
        }else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factory, String beanName) {
        try {
            return factory.getObject();
        }catch (Exception e){
            throw new BeansException("getObject failed");
        }
    }

}
