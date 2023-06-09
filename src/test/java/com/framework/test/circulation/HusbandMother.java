package com.framework.test.circulation;

import com.framework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class HusbandMother implements FactoryBean<IMother> {
    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class},
                (proxy, method, args)->"代理了");
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
