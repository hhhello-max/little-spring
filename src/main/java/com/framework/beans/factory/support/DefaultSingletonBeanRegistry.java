package com.framework.beans.factory.support;

import com.framework.beans.BeansException;
import com.framework.beans.factory.DisposableBean;
import com.framework.beans.factory.ObjectFactory;
import com.framework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存：存普通对象
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存：提前暴露，没有完全实例化的对象
     */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>();

    /**
     * 三级缓存，存代理对象
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object object = singletonObjects.get(beanName);
        if (object == null){
            object = earlySingletonObjects.get(beanName);
            if (object == null){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null){
                    object = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName, object);
                    singletonObjects.remove(beanName);
                }
            }
        }
        return object;
    }

    @Override
    public void registrySingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)){
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> keySet = disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i=disposableBeanNames.length-1; i>=0; i--){
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("destroy failed");
            }
        }
    }

}
