package spring.memcache;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController
{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping ("/greeting")
    public Greeting greeting(@RequestParam (value = "name", defaultValue = "World") String name) throws IOException
    {
        String msg = (String) MemcachedUtil.getSpyMemcachedClient().get(name);
        if(msg == null)
        {
            msg = String.format(template, name);
            MemcachedUtil.getSpyMemcachedClient().add(name, 1000, msg);
            System.out.println("Added to cache !" + name + "::" + msg);
        }
        return new Greeting(counter.incrementAndGet(), msg);
    }
}
