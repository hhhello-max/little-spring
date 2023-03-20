package com.framework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.framework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.framework.beans.factory.config.BeanDefinition;
import com.framework.beans.factory.support.BeanDefinitionRegistry;
import com.framework.stereotype.Component;

import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String[] basePackages){
        for (String basePackage : basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates){
                //解析对象的作用域singleton; prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registryBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }

    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String beanName = component.value();
        if (StrUtil.isEmpty(beanName)){
            return StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return beanName;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null){
            return scope.value();
        }
        return "";
    }


}
