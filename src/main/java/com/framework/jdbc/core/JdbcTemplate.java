package com.framework.jdbc.core;

import com.framework.jdbc.datasource.DataSourceUtils;
import com.framework.jdbc.support.JdbcAccessor;
import com.framework.transaction.TransactionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class JdbcTemplate extends JdbcAccessor implements JdbcOperations {

    private int fetchSize = -1;
    private int maxRows = -1;
    private int queryTimeout = -1;

    public JdbcTemplate() {
    }

    public JdbcTemplate(DataSource dataSource) throws Exception {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    @Override
    public <T> T execute(StatementCallback<T> action){
        Connection connection = DataSourceUtils.getConnection(getDataSource());
        try {
            Statement statement = connection.createStatement();
            applyStatementSettings(statement);
            return action.doInStatement(statement);
        }catch (SQLException e){
            throw new TransactionException("StatementCallback",e);
        }
    }

    @Override
    public void execute(String sql) {
        class ExecuteStatementCallback implements StatementCallback<Object>, SqlProvider{

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public Object doInStatement(Statement statement) throws SQLException {
                statement.execute(sql);
                return null;
            }
        }
        execute(new ExecuteStatementCallback());
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> rse) {
        class QueryStatementCallback implements StatementCallback<T>, SqlProvider{

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public T doInStatement(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery(sql);
                return rse.extractData(rs);
            }
        }
        return execute(new QueryStatementCallback());
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return query(sql, new RowMapperResultSetExtractor<>(rowMapper));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql) {
        return query(sql, new ColumnMapRowMapper());
    }

    protected void applyStatementSettings(Statement statement) throws SQLException {
        int size = getFetchSize();
        if (size != -1){
            statement.setFetchSize(size);
        }

        int maxRows = getMaxRows();
        if (maxRows != -1){
            statement.setMaxRows(maxRows);
        }
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }
}
