package com.framework.transaction.interceptor;

import com.framework.transaction.PlatformTransactionManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

public class TransactionInterceptor extends TransactionAspectSupport implements MethodInterceptor, Serializable {

    public TransactionInterceptor(PlatformTransactionManager ptm, TransactionAttributeSource tas){
        setTransactionManager(ptm);
        setTransactionAttributeSource(tas);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return invokeWithinTransaction(methodInvocation.getMethod(), methodInvocation.getThis().getClass(), methodInvocation::proceed);
    }
}
