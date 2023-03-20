package com.framework.jdbc.core;

import com.framework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnMapRowMapper implements RowMapper<Map<String, Object>>{
    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Map<String, Object> columnValues = new LinkedHashMap<>(columnCount);
        for (int i=1; i<=columnCount; i++){
            String columnName = JdbcUtils.lookupColumnName(resultSetMetaData, i);
            Object columnValue = JdbcUtils.getResultSetValue(rs, i);
            columnValues.putIfAbsent(columnName, columnValue);
        }

        return columnValues;
    }
}
