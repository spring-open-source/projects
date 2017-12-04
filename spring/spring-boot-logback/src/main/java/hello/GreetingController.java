package hello;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping ("/greeting")
    public Greeting greeting(@RequestParam (value = "name", defaultValue = "World") String name) throws IOException
    {
        LOGGER.trace("doStuff needed more information - {}", name);
        LOGGER.debug("doStuff needed to debug - {}", name);
        LOGGER.info("doStuff took input - {}", name);
        LOGGER.warn("doStuff needed to warn - {}", name);
        LOGGER.error("doStuff encountered an error with value - {}", name);

        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
