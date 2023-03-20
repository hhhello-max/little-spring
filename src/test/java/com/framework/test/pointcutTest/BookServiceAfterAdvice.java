package com.framework.test.pointcutTest;

import com.framework.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

public class BookServiceAfterAdvice implements MethodAfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截后...");
    }
}
