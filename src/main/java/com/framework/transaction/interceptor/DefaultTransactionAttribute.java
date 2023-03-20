package com.framework.transaction.interceptor;

import com.framework.transaction.support.DefaultTransactionDefinition;

public class DefaultTransactionAttribute extends DefaultTransactionDefinition implements TransactionAttribute {

    public DefaultTransactionAttribute() {
        super();
    }

    @Override
    public boolean rollbackOn(Throwable throwable) {
        return (throwable instanceof RuntimeException || throwable instanceof Error);
    }

    @Override
    public String toString() {
        return "DefaultTransactionAttribute{}";
    }
}
