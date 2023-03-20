package com.framework.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
