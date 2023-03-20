package com.framework.transaction.annotation;

import com.framework.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 解析已知事务注释类型的策略接口
 */
public interface TransactionAnnotationParser {

    /**
     * 基于该解析器理解的注释类型，解析给定方法或类的事务属性
     * @param element
     * @return
     */
    TransactionAttribute parseTransactionAnnotation(AnnotatedElement element);

}
