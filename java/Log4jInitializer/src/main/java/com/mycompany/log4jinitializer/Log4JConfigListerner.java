package com.mycompany.log4jinitializer;

import java.io.File;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.LogManager;
 
import org.apache.log4j.PropertyConfigurator;
 
/**
 *
 * @author hardiku
 * 
 * <context-param>
        <param-name>log4j-config-location</param-name>
        <param-value>WEB-INF/log4j.properties</param-value>
    </context-param>
 */
@WebListener("log4j config listener")
public class Log4JConfigListerner implements ServletContextListener {
 
    /**
     * Initialize log4j when the application is being started
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        // initialize log4j here
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        
        //configure log4j system
        PropertyConfigurator.configure(fullPath);
         
        // configure from file, and let log4j monitor the file for changes
        PropertyConfigurator.configureAndWatch(fullPath,5000);
 
        // shutdown log4j (and its monitor thread) on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                LogManager.shutdown();
            }
        });
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LogManager.shutdown();
    }  
}