package com.framework.context;

import com.framework.beans.BeansException;
import com.framework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
