package spring.memcache;

import com.github.mwarc.embeddedmemcached.EmbeddedMemcached;
import com.github.mwarc.embeddedmemcached.JMemcachedServer;
import com.github.mwarc.embeddedmemcached.MemcachedServer;
import com.google.common.base.Preconditions;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author hardiku
 */
@Component
public class MemcachedServerManager
{

    private static boolean initialized;
    private MemcachedServer server;

    public MemcachedServerManager()
    {
        this.server = new JMemcachedServer();
    }

    @PostConstruct
    protected void startServer()
    {
        System.out.println("Starting memcached server....");
        EmbeddedMemcached embeddedMemcached = Preconditions.checkNotNull(
                AnnotationUtils.findAnnotation(Application.class, EmbeddedMemcached.class),
                "MemcachedServerConfig must be used with @EmbeddedMemcached on "
                + Application.class.getName());
        String host = Preconditions.checkNotNull(embeddedMemcached.host(), "@EmbeddedMemcached host must not be null");
        int port = Preconditions.checkNotNull(embeddedMemcached.port(), "@EmbeddedMemcached port must not be null");
        Preconditions.checkArgument(port > 0, "@EmbeddedMemcached port must not be > 0");

        if(!initialized)
        {
            server.start(host, port);
            initialized = true;
        }
        System.out.println("Memcached server started.....");
    }

    @PreDestroy
    protected void stopServer()
    {
        server.clean();
        System.out.println("Memcached server stopped....");
    }
}
