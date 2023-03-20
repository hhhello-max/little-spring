package com.framework.test.event;

import com.framework.context.ApplicationListener;
import com.framework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件: "+event.getClass().getName());
    }
}
