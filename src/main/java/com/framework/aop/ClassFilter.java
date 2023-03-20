package com.framework.aop;

/**
 * 帮助切点找到给定的接口和目标类
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
