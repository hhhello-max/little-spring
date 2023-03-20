package com.framework.beans.factory.config;

import com.framework.beans.factory.BeanFactory;
import com.framework.core.convert.ConversionService;
import com.framework.util.StringValueResolver;

/**
 * 添加BeanPostProcessor
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry{

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    ConversionService getConversionService();

}
