package com.framework.aop.proxy.adapter;

import com.framework.aop.MethodAfterAdvice;
import com.framework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 实现一个环绕通知
 */
public class MethodAfterReturningAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice beforeAdvice;
    private MethodAfterAdvice afterAdvice;

    public MethodAfterReturningAdviceInterceptor() {
    }

    public MethodAfterReturningAdviceInterceptor(MethodBeforeAdvice beforeAdvice, MethodAfterAdvice afterAdvice) {
        this.beforeAdvice = beforeAdvice;
        this.afterAdvice = afterAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.beforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        methodInvocation.proceed();
        this.afterAdvice.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return null;
    }
}
