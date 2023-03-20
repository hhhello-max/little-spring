package com.framework.aop.proxy.adapter;

import com.framework.aop.MethodAfterAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor() {
    }

    public MethodAfterAdviceInterceptor(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        methodInvocation.proceed();
        this.advice.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return null;
    }
}
