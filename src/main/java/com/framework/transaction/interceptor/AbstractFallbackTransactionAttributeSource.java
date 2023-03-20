package com.framework.transaction.interceptor;

import com.framework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractFallbackTransactionAttributeSource implements TransactionAttributeSource{

    private final Map<Object, TransactionAttribute> attributeCache = new ConcurrentHashMap<>(1024);

    private static final TransactionAttribute NULL_TRANSACTION_ATTRIBUTE = new DefaultTransactionAttribute();

    @Override
    public TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass) {
        if (method.getDeclaringClass() == Object.class){
            return null;
        }
        Object cacheKey = getCacheKey(method, targetClass);
        TransactionAttribute cached = this.attributeCache.get(cacheKey);
        if (cached != null){
            if (cached == NULL_TRANSACTION_ATTRIBUTE){
                return null;
            }else {
                return cached;
            }
        }else {
            TransactionAttribute attr = computeTransactionAttribute(method, targetClass);
            if (attr == null){
                this.attributeCache.put(cacheKey, NULL_TRANSACTION_ATTRIBUTE);
            }else {
                this.attributeCache.put(cacheKey, attr);
            }
            return attr;
        }
    }

    protected Object getCacheKey(Method method, Class<?> targetClass){
        return new MethodClassKey(method, targetClass);
    }

    protected TransactionAttribute computeTransactionAttribute(Method method, Class<?> targetClass){
        if (!Modifier.isPublic(method.getModifiers())){
            return null;
        }
        TransactionAttribute attr = findTransactionAttribute(method);
        if (attr != null){
            return attr;
        }
        return findTransactionAttribute(method.getDeclaringClass());
    }

    protected abstract TransactionAttribute findTransactionAttribute(Method method);

    protected abstract TransactionAttribute findTransactionAttribute(Class<?> clazz);

}
