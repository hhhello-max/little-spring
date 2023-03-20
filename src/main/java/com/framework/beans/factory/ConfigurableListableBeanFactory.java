package com.framework.beans.factory;

import com.framework.beans.BeansException;
import com.framework.beans.factory.config.AutowireCapableBeanFactory;
import com.framework.beans.factory.config.BeanDefinition;
import com.framework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
