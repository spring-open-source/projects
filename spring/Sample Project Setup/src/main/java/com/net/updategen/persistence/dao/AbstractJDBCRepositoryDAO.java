package com.net.updategen.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

/**
 * Abstract JDCB repository DAO
 *
 * @author hardiku
 */
public abstract class AbstractJDBCRepositoryDAO
{

    @Autowired
    protected NamedParameterJdbcOperations jdbcTemplate;

//    public AbstractJDBCRepositoryDAO(NamedParameterJdbcOperations jdbcTemplate)
//    {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    public NamedParameterJdbcOperations getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

}
