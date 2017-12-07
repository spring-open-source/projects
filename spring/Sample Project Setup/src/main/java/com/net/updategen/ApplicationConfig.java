package com.net.updategen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * This class is a wrapper for getting application configuration properties
 * Note:Do not create
 *
 * @author hardiku
 */
@Component
public class ApplicationConfig
{

    private final Environment env;

    @Autowired
    public ApplicationConfig(Environment env)
    {
        this.env = env;
    }

    public String getProperty(String propName)
    {
        return (env).getProperty(propName);
    }

}
