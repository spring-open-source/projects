package watchservice;

import org.apache.log4j.Logger;



/**
 *
 * @author hardiku
 */
public class Log4jConfigFileWatcher {
    

    //This ensures singleton instance of configurator
    private final static Log4jConfigFileWatcher INSTANCE = new Log4jConfigFileWatcher();
 
    private static final String LOG_FILE_PATH = "D:\\hardik\\projects\\Log4jExample\\src\\log4j.xml";
    
    public static Log4jConfigFileWatcher getInstance()
    {
        return INSTANCE;
    }
 
    //This method will start the watcher service of log4j.xml file and also configure the loggers
    public void initilize(final String file) {
        try
        {
            //Create the watch service thread and start it.
            //I will suggest to use some logic here which will check if this thread is still alive;
            //If thread is killed then restart the thread
            SimpleFileChangeWatcher listner = new SimpleFileChangeWatcher(file);
             
            //Start the thread
            new Thread(listner).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
     
    public static void main(String[] args) throws InterruptedException
    {
        //Configure logger service
        Log4jConfigFileWatcher.getInstance().initilize(LOG_FILE_PATH);
         
        //Get logger instance
        Logger LOGGER = Logger.getLogger(Log4jConfigFileWatcher.class.getName());
         
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
