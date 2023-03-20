package com.framework.transaction.interceptor;

import com.framework.transaction.TransactionDefinition;

public interface TransactionAttribute extends TransactionDefinition {

    boolean rollbackOn(Throwable throwable);

}
