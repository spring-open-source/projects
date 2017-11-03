package com.stackhunter.jdbc.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExecutionPlanResultSetExtractor implements ResultSetExtractor<List<ExecutionPlan>> {

    private final ExecutionPlanRowMapper rowMapper;
    
    public ExecutionPlanResultSetExtractor(String sql, long duration) {
        rowMapper = new ExecutionPlanRowMapper(sql, duration);
    }
    
    @Override
    public List<ExecutionPlan> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<ExecutionPlan> executionPlan = new ArrayList<ExecutionPlan>();
        for (int i = 0; rs.next(); i++) {
            executionPlan.add(rowMapper.mapRow(rs, i));
        }
        return executionPlan;
    }

}
