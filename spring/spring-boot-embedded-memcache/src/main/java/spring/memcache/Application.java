package spring.memcache;

import com.github.mwarc.embeddedmemcached.EmbeddedMemcached;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EmbeddedMemcached (host = "127.0.0.1", port = 11214)
@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
