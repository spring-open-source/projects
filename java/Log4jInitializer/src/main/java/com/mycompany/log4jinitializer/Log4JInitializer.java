package com.mycompany.log4jinitializer;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
/**
 *
 * @author hardiku
 * 
 * Package this in a jar, put it on the Tomcat classpath, and now you can configure it in your Tomcat serverl.xml.
 *  <Server>
        <Listener className="Log4JInitializer" propertiesFile="/path/to/log4j.properties"/>
    </Server>
 */ 
public class Log4JInitializer implements LifecycleListener
{
    private String propertiesFile;
 
    public String getPropertiesFile()
    {
        return this.propertiesFile;
    }
 
    public void setPropertiesFile(String propertiesFile)
    {
        this.propertiesFile = propertiesFile;
    }
 
    @Override
    public void lifecycleEvent(LifecycleEvent event)
    {
        if (Lifecycle.BEFORE_START_EVENT.equals(event.getType()))
            initializeLog4j();
    }
 
    private void initializeLog4j()
    {
        // configure from file, and let log4j monitor the file for changes
        PropertyConfigurator.configureAndWatch(propertiesFile);
 
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
}
