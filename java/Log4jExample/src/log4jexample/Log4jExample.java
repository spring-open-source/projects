/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jexample;

import org.apache.log4j.Logger;

/**
 *
 * @author hardiku
 */
public class Log4jExample {

    final static Logger logger = Logger.getLogger(Log4jExample.class);

    public static void main(String[] args) {

        Log4jExample obj = new Log4jExample();
        obj.runMe("mkyong");
        SpecialLogger spLogger = new SpecialLogger();
        spLogger.runMe(SpecialLogger.class.getName());
    }

    private void runMe(String parameter) {

        if (logger.isDebugEnabled()) {
            logger.debug("This is debug : " + parameter);
        }

        if (logger.isInfoEnabled()) {
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);

    }

}
