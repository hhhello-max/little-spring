package com.framework.jdbc.core;

import cn.hutool.core.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    private final int rowsExpected;

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        this(rowMapper, 0);
    }

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
        Assert.notNull(rowMapper, "RowMapper is required");
        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }

    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        List<T> result = this.rowsExpected>0? new ArrayList<>(this.rowsExpected):new ArrayList<>();
        int rowNum = 0;
        while (rs.next()){
            result.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }
}