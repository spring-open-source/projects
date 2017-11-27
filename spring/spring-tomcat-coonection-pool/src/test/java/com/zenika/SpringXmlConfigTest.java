package com.zenika;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringXmlConfigTest {

    @Autowired
    JdbcOperations tpl;

    @Test
    public void datasource() {
        tpl.queryForInt("select 1");
    }

}
