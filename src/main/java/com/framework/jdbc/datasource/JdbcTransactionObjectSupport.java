package com.framework.jdbc.datasource;

/**
 *
 * @description Convenient base class for JDBC-aware transaction objects.
 *
 */
public abstract class JdbcTransactionObjectSupport {

    private ConnectionHolder connectionHolder;

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    public boolean hasConnectionHolder() {
        return null != this.connectionHolder;
    }

}
