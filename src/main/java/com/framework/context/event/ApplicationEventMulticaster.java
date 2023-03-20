package com.framework.context.event;

import com.framework.context.ApplicationEvent;
import com.framework.context.ApplicationListener;

/**
 * 事件的广播器
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicasterEvent(ApplicationEvent event);

}
