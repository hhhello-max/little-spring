package com.framework.transaction.support;

public class DefaultTransactionStatus extends AbstractTransactionStatus{

    private final Object transaction;
    private final boolean newTransaction;

    public DefaultTransactionStatus(Object transaction, boolean newTransaction) {
        this.transaction = transaction;
        this.newTransaction = newTransaction;
    }

    public Object getTransaction(){
        return transaction;
    }

    @Override
    public boolean isNewTransaction() {
        return hasSavepoint() && this.newTransaction;
    }

    @Override
    public boolean hasSavepoint() {
        return this.transaction != null;
    }
}
