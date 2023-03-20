package com.framework.aop;

import java.lang.reflect.Method;

/**
 * 找到表达式范围内匹配的目标类和方法
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
