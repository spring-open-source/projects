package com.net.updategen.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

/**
 *
 * @author hardiku
 */
public class SampleDAO extends AbstractJDBCRepositoryDAO
{
    //private NamedParameterJdbcTemplate jdbcTemplate;

    public SampleDAO()
    {

    }

    public void saveObject()
    {
        jdbcTemplate.execute("SELECT 1", new PreparedStatementCallback<ResultSet>()
        {
            @Override
            public ResultSet doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
            {
                return ps.executeQuery();
            }
        });
    }
}
