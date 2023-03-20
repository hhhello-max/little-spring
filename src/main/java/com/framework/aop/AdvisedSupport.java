package com.framework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 将代理、拦截、匹配的各项属性包装，方法Proxy实现类中使用
 */
public class AdvisedSupport {

    //代理方式false:JDK, true:CGlib
    private boolean proxyStrategy = false;
    //被代理的目标对象
    private TargetSource targetSource;
    //方法拦截器
    private MethodInterceptor methodInterceptor;
    //方法匹配器
    private MethodMatcher methodMatcher;

    public void setProxyStrategy(boolean proxyStrategy) {
        this.proxyStrategy = proxyStrategy;
    }

    public boolean isProxyStrategy(){
        return proxyStrategy;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
