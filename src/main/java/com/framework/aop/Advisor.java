package com.framework.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor是Pointcut和Advice的组合
 */
public interface Advisor {

    Advice getAdvice();

}
