package com.framework.aop.aspectj;

import com.framework.aop.Pointcut;
import com.framework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Pointcut用于获取JoinPoint, Advice取决于JoinPoint执行什么操作
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;
    private String expression;

    @Override
    public Pointcut getPointcut() {
        return pointcut!=null? pointcut:new AspectJExpressionPointcut(expression);
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
