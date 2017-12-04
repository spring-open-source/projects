package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://lankydanblog.com/2017/08/31/configuring-logback-with-spring-boot/
 *
 * @author hardiku
 */
@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
