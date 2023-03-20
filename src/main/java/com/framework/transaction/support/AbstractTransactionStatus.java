package com.framework.transaction.support;

import com.framework.transaction.SavepointManager;
import com.framework.transaction.TransactionException;
import com.framework.transaction.TransactionStatus;

import java.io.IOException;

public abstract class AbstractTransactionStatus implements TransactionStatus {

    private boolean rollbackOnly = false;
    private boolean completed = false;
    private Object savepoint;

    @Override
    public Object createSavepoint() throws TransactionException {
        return getSavepointManager().createSavepoint();
    }

    @Override
    public void rollbackToSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().rollbackToSavepoint(savepoint);
    }

    @Override
    public void releaseSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().releaseSavepoint(savepoint);
    }

    @Override
    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    @Override
    public boolean isRollbackOnly() {
        return this.rollbackOnly;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void flush() throws IOException {

    }

    protected SavepointManager getSavepointManager(){
        throw new TransactionException("This transaction does not support save points");
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Object getSavepoint() {
        return savepoint;
    }

    public void setSavepoint(Object savepoint) {
        this.savepoint = savepoint;
    }
}
