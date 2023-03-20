package com.framework.transaction.support;

import com.framework.transaction.PlatformTransactionManager;
import com.framework.transaction.TransactionDefinition;
import com.framework.transaction.TransactionException;
import com.framework.transaction.TransactionStatus;

import java.io.Serializable;

public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager, Serializable {
    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        Object transaction = doGetTransaction();
        if (transaction != null){
            definition = new DefaultTransactionDefinition();
        }
        if (definition.getTimeout() < TransactionDefinition.TIMEOUT_DEFAULT){
            throw new TransactionException("Invalid transaction timeout");
        }
        DefaultTransactionStatus status = newTransactionStatus(definition, transaction, true);
        doBegin(transaction, definition);
        return status;
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()){
            throw new IllegalArgumentException("completed");
        }
        DefaultTransactionStatus defaultTransactionStatus = (DefaultTransactionStatus) status;
        processCommit(defaultTransactionStatus);
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()){
            throw new IllegalArgumentException("completed");
        }
        DefaultTransactionStatus defaultTransactionStatus = (DefaultTransactionStatus) status;
        processRollback(defaultTransactionStatus, false);
    }

    protected DefaultTransactionStatus newTransactionStatus(TransactionDefinition definition, Object transaction, boolean newTransaction){
        return new DefaultTransactionStatus(transaction, newTransaction);
    }

    private void processCommit(DefaultTransactionStatus status) throws TransactionException{
        doCommit(status);
    }

    private void processRollback(DefaultTransactionStatus status, boolean unexpected){
        doRollback(status);
    }

    protected abstract Object doGetTransaction() throws TransactionException;

    protected abstract void doCommit(DefaultTransactionStatus status) throws TransactionException;

    protected abstract void doRollback(DefaultTransactionStatus status) throws TransactionException;

    protected abstract void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException;

}
