package com.zenika;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringJavaConfigTest {

    @Autowired JdbcOperations tpl;

    @Test public void datasource() {
        tpl.queryForInt("select 1");
    }


    @Configuration
    public static class DataAccessConfiguration {

        @Bean(destroyMethod = "close")
        public DataSource datasource() {
            org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
            ds.setDriverClassName("org.h2.Driver");
            ds.setUrl("jdbc:h2:java-config");
            ds.setUsername("sa");
            ds.setPassword("");
            ds.setInitialSize(5);
            ds.setMaxActive(10);
            ds.setMaxIdle(5);
            ds.setMinIdle(2);
            return ds;
        }

        @Bean public JdbcOperations tpl() {
            return new JdbcTemplate(datasource());
        }

    }
}
