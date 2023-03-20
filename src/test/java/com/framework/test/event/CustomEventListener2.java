package com.framework.test.event;

import com.framework.context.ApplicationListener;

public class CustomEventListener2 implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("我也收到了消息");
    }
}
