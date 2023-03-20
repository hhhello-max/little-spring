package com.framework.test.event;

import com.framework.context.ApplicationListener;
import com.framework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件: "+event.getClass().getName());
    }
}
