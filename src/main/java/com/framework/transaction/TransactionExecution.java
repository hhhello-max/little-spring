package com.framework.transaction;

public interface TransactionExecution {

    boolean isNewTransaction();

    void setRollbackOnly();

    boolean isRollbackOnly();

    boolean isCompleted();

}
