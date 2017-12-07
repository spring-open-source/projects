package com.net.updategen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Standard spring boot java config class
 *
 * @author hardiku
 */
@Configuration
@ComponentScan ("com.test.updategen")
public class ConfigurationProvider
{

    @Autowired
    Environment env;

}
