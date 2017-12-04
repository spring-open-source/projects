package com.example.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestResources
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class loader = TestResources.class;

    @RequestMapping ("/")
    @ResponseBody
    public String getCreateIndexView()
    {
        //	System.out.println("thread name in / ::: "+Thread.currentThread());
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");

        try
        {
            return autoSaving();
        }
        catch(Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }

    private String autoSaving() throws Exception
    {

        Properties prop = new Properties();
        InputStream input = null;

        try
        {
            String filename = "db.properties";
            String configDir = System.getProperty("configdir");
            input = new FileInputStream(configDir + File.separator + filename);
            if(input == null)
            {
                System.out.println("Sorry, unable to find " + configDir + File.separator + filename);
                return "";
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            logger.info(prop.getProperty("spring.datasource.username"));
            logger.info(prop.getProperty("spring.datasource.password"));

            return prop.getProperty("spring.datasource.username");

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        return "";

    }

}
