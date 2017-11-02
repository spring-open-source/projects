package com.stackhunter.jdbc.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExecutionPlanRowMapper implements RowMapper<ExecutionPlan> {

    private final String sql;
    private final long duration;
    
    public ExecutionPlanRowMapper(String sql, long duration) {
        this.sql = sql;
        this.duration = duration;
    }

    @Override
    public ExecutionPlan mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExecutionPlan o = new ExecutionPlan();
        
        o.setId(rs.getLong("id"));
        o.setSelectType(rs.getString("select_type"));
        o.setTable(rs.getString("table"));
//        o.setPartitions(rs.getString("partitions"));            
        o.setType(rs.getString("type"));
        o.setPossibleKeys(rs.getString("possible_keys"));
        o.setKey(rs.getString("key"));
        o.setKeyLength(rs.getString("key_len"));
        o.setRef(rs.getString("ref"));
        o.setRows(rs.getInt("rows"));
//        o.setFiltered(rs.getInt("filtered"));           
        o.setExtra(rs.getString("Extra"));
        o.setDuration(duration);
        o.setSql(sql);
        
        return o;
    }

}
