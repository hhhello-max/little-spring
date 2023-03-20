package com.framework.jdbc.datasource;

import java.sql.Connection;

public interface ConnectionHandle {

    Connection getConnection();

    default void releaseConnection(Connection con) {

    }

}
