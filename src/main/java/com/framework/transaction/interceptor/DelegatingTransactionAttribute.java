package com.framework.transaction.interceptor;

import com.framework.transaction.TransactionDefinition;
import com.framework.transaction.support.DelegatingTransactionDefinition;

import java.io.Serializable;

public abstract class DelegatingTransactionAttribute extends DelegatingTransactionDefinition implements TransactionAttribute, Serializable {

    private final TransactionAttribute targetAttribute;

    protected DelegatingTransactionAttribute(TransactionAttribute targetAttribute) {
        super(targetAttribute);
        this.targetAttribute = targetAttribute;
    }

    @Override
    public boolean rollbackOn(Throwable throwable) {
        return this.targetAttribute.rollbackOn(throwable);
    }
}
