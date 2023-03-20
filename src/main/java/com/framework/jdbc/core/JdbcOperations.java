package com.framework.jdbc.core;

import java.util.List;
import java.util.Map;

public interface JdbcOperations {

    <T> T execute(StatementCallback<T> action) throws Exception;

    void execute(String sql) throws Exception;

    <T> T query(String sql, ResultSetExtractor<T> rse);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    List<Map<String, Object>> queryForList(String sql);

}
