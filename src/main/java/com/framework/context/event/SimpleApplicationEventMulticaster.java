package com.framework.context.event;

import com.framework.beans.factory.BeanFactory;
import com.framework.context.ApplicationEvent;
import com.framework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicasterEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}
