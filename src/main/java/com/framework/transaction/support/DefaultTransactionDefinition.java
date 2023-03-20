package com.framework.transaction.support;

import com.framework.transaction.TransactionDefinition;

import java.io.Serializable;

public class DefaultTransactionDefinition implements TransactionDefinition, Serializable {

    private int propagationBehavior = PROPAGATION_REQUIRED;

    private int isolationLevel = ISOLATION_DEFAULT;

    private int timeout = TIMEOUT_DEFAULT;

    private boolean readonly = false;

    private String name;

    public DefaultTransactionDefinition() {
    }

    public DefaultTransactionDefinition(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    public DefaultTransactionDefinition(TransactionDefinition transactionDefinition) {
        this.propagationBehavior = transactionDefinition.getPropagationBehavior();
        this.isolationLevel = transactionDefinition.getIsolationLevel();
        this.timeout = transactionDefinition.getTimeout();
        this.readonly = transactionDefinition.isReadOnly();
        this.name = transactionDefinition.getName();
    }

    @Override
    public int getPropagationBehavior() {
        return 0;
    }

    @Override
    public int getIsolationLevel() {
        return 0;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
