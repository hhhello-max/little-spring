package com.framework.test.pointcutTest;

import com.framework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BookServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截前..."+ method.getName());
    }
}
