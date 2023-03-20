package com.framework.test.pointcutTest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class BookServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("拦截到方法了，先执行我的再说");
        return methodInvocation.proceed();
    }
}
