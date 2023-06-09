package com.framework.transaction.interceptor;

import java.lang.reflect.Method;

public interface TransactionAttributeSource {

    TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass);

}
