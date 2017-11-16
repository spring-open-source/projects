package com.howtodoinjava.demo;

import org.apache.log4j.Logger;

public class Log4jConfigReloadExample 
{
	private static final String LOG_FILE_PATH = "C:/Lokesh/Setup/workspace/Log4jReloadExample/log4j-config.xml";
	
	public static void main(String[] args) throws InterruptedException 
	{
		//Configure logger service
		Log4jConfigurator.getInstance().initilize(LOG_FILE_PATH);
		
		//Get logger instance
		Logger LOGGER = Logger.getLogger(Log4jConfigReloadExample.class);
		
		//Print the log messages and wait for log4j changes
		while(true)
		{
			//Debug level log message
			LOGGER.debug("A debug message !!");
			//Info level log message
			LOGGER.info("A info message !!");
			
			//Wait between log messages
			Thread.sleep(2000);
		}
	}
}
